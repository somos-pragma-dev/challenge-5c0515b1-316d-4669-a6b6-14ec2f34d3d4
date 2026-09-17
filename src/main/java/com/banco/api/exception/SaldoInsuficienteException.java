package com.banco.api.exception;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends RuntimeException {
    private final String numeroCuenta;
    private final BigDecimal saldoActual;
    private final BigDecimal montoRequerido;

    public SaldoInsuficienteException(String numeroCuenta, BigDecimal saldoActual, BigDecimal montoRequerido) {
        super(String.format("Saldo insuficiente en cuenta %s: saldo actual=%s, monto requerido=%s", 
                numeroCuenta, saldoActual, montoRequerido));
        this.numeroCuenta = numeroCuenta;
        this.saldoActual = saldoActual;
        this.montoRequerido = montoRequerido;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public BigDecimal getMontoRequerido() {
        return montoRequerido;
    }

    public BigDecimal getDeficit() {
        return montoRequerido.subtract(saldoActual);
    }
}