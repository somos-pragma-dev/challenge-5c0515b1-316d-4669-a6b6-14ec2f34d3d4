package com.banco.api.service;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.dto.TransferenciaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.exception.SaldoInsuficienteException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.TransaccionRepository;
import com.banco.api.util.IdempotenciaUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;
    private final IdempotenciaUtil idempotenciaUtil;

    public CuentaService(CuentaRepository cuentaRepository,
                         TransaccionRepository transaccionRepository,
                         IdempotenciaUtil idempotenciaUtil) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
        this.idempotenciaUtil = idempotenciaUtil;
    }

    public CuentaDTO obtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
            .orElseThrow(() -> new CuentaNoEncontradaException(id));
        return CuentaDTO.fromEntity(cuenta);
    }

    public List<CuentaDTO> listarCuentas() {
        return cuentaRepository.findAll().stream()
            .map(CuentaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Transactional
    public CuentaDTO realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, 
                                           BigDecimal monto, String claveIdempotencia) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        if (claveIdempotencia != null && !idempotenciaUtil.esRequisicionIdempotente(claveIdempotencia)) {
            throw new IllegalArgumentException("Clave de idempotencia ya utilizada");
        }

        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId)
            .orElseThrow(() -> new CuentaNoEncontradaException(cuentaOrigenId));

        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId)
            .orElseThrow(() -> new CuentaNoEncontradaException(cuentaDestinoId));

        if (cuentaOrigen.getEstado() != EstadoCuenta.ACTIVA) {
            throw new IllegalStateException("La cuenta de origen no está activa");
        }

        if (cuentaDestino.getEstado() != EstadoCuenta.ACTIVA) {
            throw new IllegalStateException("La cuenta de destino no está activa");
        }

        if (!cuentaOrigen.tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException(
                cuentaOrigen.getNumeroCuenta(),
                cuentaOrigen.getSaldo(),
                monto
            );
        }

        BigDecimal comision = calcularComision(monto);
        BigDecimal montoTotal = monto.add(comision);

        cuentaOrigen.debitar(montoTotal);
        cuentaDestino.acreditar(monto);

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        if (claveIdempotencia != null) {
            idempotenciaUtil.registrarRequisicion(claveIdempotencia, 
                new IdempotenciaUtil.ResultadoOperacion(true, "Transferencia exitosa"));
        }

        return CuentaDTO.fromEntity(cuentaOrigen);
    }

    public CuentaDTO consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
        return CuentaDTO.fromEntity(cuenta);
    }

    public TransferenciaDTO realizarTransferencia(String numeroCuentaOrigen, 
                                                   String numeroCuentaDestino,
                                                   BigDecimal monto,
                                                   String descripcion,
                                                   String claveIdempotencia) {
        Cuenta cuentaOrigen = cuentaRepository.findByNumeroCuenta(numeroCuentaOrigen)
            .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuentaOrigen));
        Cuenta cuentaDestino = cuentaRepository.findByNumeroCuenta(numeroCuentaDestino)
            .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuentaDestino));

        if (!cuentaOrigen.tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException(cuentaOrigen.getNumeroCuenta(), cuentaOrigen.getSaldo(), monto);
        }

        cuentaOrigen.debitar(monto);
        cuentaDestino.acreditar(monto);

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        return new TransferenciaDTO(numeroCuentaOrigen, numeroCuentaDestino, monto, descripcion, claveIdempotencia);
    }

    private BigDecimal calcularComision(BigDecimal monto) {
        return monto.multiply(new BigDecimal("0.01"));
    }

    public boolean cuentaExiste(String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }

    public boolean cuentaActiva(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .map(c -> c.getEstado() == EstadoCuenta.ACTIVA)
            .orElse(false);
    }
}