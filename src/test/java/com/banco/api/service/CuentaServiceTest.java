package com.banco.api.service;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.exception.SaldoInsuficienteException;
import com.banco.api.model.Cliente;
import com.banco.api.model.Cliente.EstadoCliente;
import com.banco.api.model.Cliente.TipoIdentificacion;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.model.Cuenta.TipoCuenta;
import com.banco.api.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    private Cuenta cuentaMock;
    private Cliente clienteMock;

    @BeforeEach
    void setUp() {
        clienteMock = new Cliente();
        clienteMock.setId(1L);
        clienteMock.setIdentificacion("12345678");
        clienteMock.setNombre("Juan");
        clienteMock.setApellido("Perez");
        clienteMock.setEmail("juan.perez@banco.com");
        clienteMock.setTipoIdentificacion(TipoIdentificacion.CEDULA);
        clienteMock.setEstado(EstadoCliente.ACTIVO);
        clienteMock.setFechaCreacion(LocalDateTime.now());

        cuentaMock = new Cuenta();
        cuentaMock.setId(1L);
        cuentaMock.setNumeroCuenta("1234567890");
        cuentaMock.setSaldo(new BigDecimal("1000.00"));
        cuentaMock.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaMock.setEstado(EstadoCuenta.ACTIVA);
        cuentaMock.setCliente(clienteMock);
        cuentaMock.setFechaCreacion(LocalDateTime.now());
    }

    @Test
    void testObtenerCuentaPorId_CuentaExistente_RetornaCuentaDTO() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaMock));

        CuentaDTO resultado = cuentaService.obtenerCuentaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("1234567890", resultado.numeroCuenta());
        assertEquals(new BigDecimal("1000.00"), resultado.saldo());
        assertEquals("AHORROS", resultado.tipoCuenta());
        verify(cuentaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerCuentaPorId_CuentaNoExistente_LanzaExcepcion() {
        when(cuentaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CuentaNoEncontradaException.class, () -> {
            cuentaService.obtenerCuentaPorId(999L);
        });

        verify(cuentaRepository, times(1)).findById(999L);
    }

    @Test
    void testListarCuentas_RetornaListaDeCuentas() {
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(2L);
        cuenta2.setNumeroCuenta("0987654321");
        cuenta2.setSaldo(new BigDecimal("2000.00"));
        cuenta2.setTipoCuenta(TipoCuenta.CORRIENTE);
        cuenta2.setEstado(EstadoCuenta.ACTIVA);
        cuenta2.setCliente(clienteMock);

        when(cuentaRepository.findAll()).thenReturn(java.util.List.of(cuentaMock, cuenta2));

        java.util.List<CuentaDTO> resultado = cuentaService.listarCuentas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(cuentaRepository, times(1)).findAll();
    }

    @Test
    void testRealizarTransferencia_TransferenciaExitosa_DebitaYAcredita() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setNumeroCuenta("1234567890");
        cuentaOrigen.setSaldo(new BigDecimal("1000.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.ACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        Cuenta cuentaDestino = new Cuenta();
        cuentaDestino.setId(2L);
        cuentaDestino.setNumeroCuenta("0987654321");
        cuentaDestino.setSaldo(new BigDecimal("500.00"));
        cuentaDestino.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaDestino.setEstado(EstadoCuenta.ACTIVA);
        cuentaDestino.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaRepository.findById(2L)).thenReturn(Optional.of(cuentaDestino));
        when(cuentaRepository.save(any(Cuenta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String claveIdempotencia = UUID.randomUUID().toString();
        CuentaDTO resultado = cuentaService.realizarTransferencia(
            1L, 2L, new BigDecimal("300.00"), claveIdempotencia
        );

        assertNotNull(resultado);
        assertEquals(new BigDecimal("700.00"), cuentaOrigen.getSaldo());
        assertEquals(new BigDecimal("800.00"), cuentaDestino.getSaldo());
        verify(cuentaRepository, times(2)).findById(any());
        verify(cuentaRepository, times(2)).save(any(Cuenta.class));
    }

    @Test
    void testRealizarTransferencia_SaldoInsuficiente_LanzaExcepcion() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setNumeroCuenta("1234567890");
        cuentaOrigen.setSaldo(new BigDecimal("100.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.ACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaRepository.findById(2L)).thenReturn(Optional.of(cuentaMock));

        assertThrows(SaldoInsuficienteException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 2L, new BigDecimal("500.00"), UUID.randomUUID().toString()
            );
        });

        verify(cuentaRepository, times(1)).findById(1L);
        verify(cuentaRepository, never()).save(any(Cuenta.class));
    }

    @Test
    void testRealizarTransferencia_CuentaOrigenNoExiste_LanzaExcepcion() {
        when(cuentaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CuentaNoEncontradaException.class, () -> {
            cuentaService.realizarTransferencia(
                999L, 2L, new BigDecimal("100.00"), UUID.randomUUID().toString()
            );
        });

        verify(cuentaRepository, times(1)).findById(999L);
    }

    @Test
    void testRealizarTransferencia_CuentaDestinoNoExiste_LanzaExcepcion() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaMock));
        when(cuentaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CuentaNoEncontradaException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 999L, new BigDecimal("100.00"), UUID.randomUUID().toString()
            );
        });

        verify(cuentaRepository, times(1)).findById(999L);
    }

    @Test
    void testRealizarTransferencia_MontoNegativo_LanzaExcepcion() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setSaldo(new BigDecimal("1000.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.ACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaRepository.findById(2L)).thenReturn(Optional.of(cuentaMock));

        assertThrows(IllegalArgumentException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 2L, new BigDecimal("-100.00"), UUID.randomUUID().toString()
            );
        });
    }

    @Test
    void testRealizarTransferencia_CuentaInactiva_LanzaExcepcion() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setNumeroCuenta("1234567890");
        cuentaOrigen.setSaldo(new BigDecimal("1000.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.INACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));

        assertThrows(IllegalStateException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 2L, new BigDecimal("100.00"), UUID.randomUUID().toString()
            );
        });
    }
}