package com.banco.api.exception;

import lombok.Getter;

@Getter
public class CuentaNoEncontradaException extends RuntimeException {
    
    private final String numeroCuenta;
    private final String codigoError;
    
    public CuentaNoEncontradaException(String numeroCuenta) {
        super(String.format("La cuenta con número %s no fue encontrada", numeroCuenta));
        this.numeroCuenta = numeroCuenta;
        this.codigoError = "CUENTA_NO_ENCONTRADA";
    }
    
    public CuentaNoEncontradaException(String numeroCuenta, String mensaje) {
        super(mensaje);
        this.numeroCuenta = numeroCuenta;
        this.codigoError = "CUENTA_NO_ENCONTRADA";
    }
    
    public CuentaNoEncontradaException(String numeroCuenta, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.numeroCuenta = numeroCuenta;
        this.codigoError = "CUENTA_NO_ENCONTRADA";
    }
    
    public CuentaNoEncontradaException(Long id) {
        super(String.format("La cuenta con ID %d no fue encontrada", id));
        this.numeroCuenta = null;
        this.codigoError = "CUENTA_NO_ENCONTRADA";
    }
    
    public CuentaNoEncontradaException(Long id, String mensaje) {
        super(mensaje);
        this.numeroCuenta = null;
        this.codigoError = "CUENTA_NO_ENCONTRADA";
    }
    
    public String getMensajeTecnico() {
        return String.format("CuentaNoEncontradaException: numeroCuenta=%s, codigo=%s", 
            numeroCuenta, codigoError);
    }
}