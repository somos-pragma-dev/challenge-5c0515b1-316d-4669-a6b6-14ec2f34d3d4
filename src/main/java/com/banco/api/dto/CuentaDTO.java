package com.banco.api.dto;

import com.banco.api.model.Cuenta;
import java.math.BigDecimal;

public record CuentaDTO(
    Long id,
    String numeroCuenta,
    BigDecimal saldo,
    String tipoCuenta,
    String estado,
    Long clienteId,
    String clienteNombre
) {
    public static CuentaDTO fromEntity(Cuenta cuenta) {
        if (cuenta == null) return null;
        Long clienteId = cuenta.getCliente() != null ? cuenta.getCliente().getId() : null;
        String clienteNombre = cuenta.getCliente() != null 
            ? cuenta.getCliente().getNombreCompleto() 
            : null;
        return new CuentaDTO(
            cuenta.getId(),
            cuenta.getNumeroCuenta(),
            cuenta.getSaldo(),
            cuenta.getTipoCuenta() != null ? cuenta.getTipoCuenta().name() : null,
            cuenta.getEstado() != null ? cuenta.getEstado().name() : null,
            clienteId,
            clienteNombre
        );
    }
}