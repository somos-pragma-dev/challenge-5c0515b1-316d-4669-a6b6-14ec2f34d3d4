package com.banco.api.controller;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.model.Cuenta.TipoCuenta;
import com.banco.api.service.CuentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Test
    void testObtenerCuentaPorId_CuentaExiste_Retorna200() throws Exception {
        CuentaDTO cuentaDTO = new CuentaDTO(
            1L,
            "1234567890",
            new BigDecimal("1000.00"),
            TipoCuenta.AHORROS.name(),
            EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            1L,
            "Juan",
            "Perez",
            "juan.perez@banco.com"
        );

        when(cuentaService.obtenerCuentaPorId(1L)).thenReturn(cuentaDTO);

        mockMvc.perform(get("/api/cuentas/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.numeroCuenta").value("1234567890"))
            .andExpect(jsonPath("$.saldo").value(1000.00))
            .andExpect(jsonPath("$.tipoCuenta").value("AHORROS"));
    }

    @Test
    void testObtenerCuentaPorId_CuentaNoExiste_Retorna404() throws Exception {
        when(cuentaService.obtenerCuentaPorId(999L))
            .thenThrow(new com.banco.api.exception.CuentaNoEncontradaException("Cuenta no encontrada"));

        mockMvc.perform(get("/api/cuentas/999")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testListarCuentas_RetornaListaDeCuentas_Retorna200() throws Exception {
        CuentaDTO cuenta1 = new CuentaDTO(
            1L, "1234567890", new BigDecimal("1000.00"),
            TipoCuenta.AHORROS.name(), EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(), LocalDateTime.now(), 1L, "Juan", "Perez", "juan@email.com"
        );
        CuentaDTO cuenta2 = new CuentaDTO(
            2L, "0987654321", new BigDecimal("2000.00"),
            TipoCuenta.CORRIENTE.name(), EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(), LocalDateTime.now(), 2L, "Maria", "Gomez", "maria@email.com"
        );

        when(cuentaService.listarCuentas()).thenReturn(List.of(cuenta1, cuenta2));

        mockMvc.perform(get("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].numeroCuenta").value("1234567890"))
            .andExpect(jsonPath("$[1].numeroCuenta").value("0987654321"));
    }

    @Test
    void testRealizarTransferencia_TransferenciaExitosa_Retorna200() throws Exception {
        CuentaDTO cuentaOrigen = new CuentaDTO(
            1L, "1234567890", new BigDecimal("1000.00"),
            TipoCuenta.AHORROS.name(), EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(), LocalDateTime.now(), 1L, "Juan", "Perez", "juan@email.com"
        );

        when(cuentaService.realizarTransferencia(eq(1L), eq(2L), any(BigDecimal.class), any()))
            .thenReturn(cuentaOrigen);

        String jsonRequest = "{\"cuentaOrigenId\":1,\"cuentaDestinoId\":2,\"monto\":500.00,\"descripcion\":\"Transferencia de prueba\"}";

        mockMvc.perform(post("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("X-Idempotency-Key", "unique-key-123"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.saldo").value(1000.00));
    }

    @Test
    void testRealizarTransferencia_SaldoInsuficiente_Retorna400() throws Exception {
        when(cuentaService.realizarTransferencia(eq(1L), eq(2L), any(BigDecimal.class), any()))
            .thenThrow(new com.banco.api.exception.SaldoInsuficienteException("Saldo insuficiente"));

        String jsonRequest = "{\"cuentaOrigenId\":1,\"cuentaDestinoId\":2,\"monto\":5000.00,\"descripcion\":\"Transferencia grande\"}";

        mockMvc.perform(post("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("X-Idempotency-Key", "unique-key-456"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testRealizarTransferencia_CuentaOrigenNoExiste_Retorna404() throws Exception {
        when(cuentaService.realizarTransferencia(eq(999L), eq(2L), any(BigDecimal.class), any()))
            .thenThrow(new com.banco.api.exception.CuentaNoEncontradaException("Cuenta origen no encontrada"));

        String jsonRequest = "{\"cuentaOrigenId\":999,\"cuentaDestinoId\":2,\"monto\":100.00,\"descripcion\":\"Transferencia\"}";

        mockMvc.perform(post("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("X-Idempotency-Key", "unique-key-789"))
            .andExpect(status().isNotFound());
    }
}