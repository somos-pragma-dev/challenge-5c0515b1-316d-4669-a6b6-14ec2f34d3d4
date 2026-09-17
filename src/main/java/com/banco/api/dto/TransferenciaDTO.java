package com.banco.api.dto;

import com.banco.api.model.Cuenta;
import com.banco.api.model.Transaccion;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciaDTO {

    private String cuentaOrigen;
    private String cuentaDestino;
    private BigDecimal monto;
    private String descripcion;
    private String claveIdempotencia;

    public static TransferenciaDTO fromEntity(Transaccion transaccion) {
        if (transaccion == null) {
            return null;
        }
        return TransferenciaDTO.builder()
                .cuentaOrigen(transaccion.getCuentaOrigen() != null ? 
                        transaccion.getCuentaOrigen().getNumeroCuenta() : null)
                .cuentaDestino(transaccion.getCuentaDestino() != null ? 
                        transaccion.getCuentaDestino().getNumeroCuenta() : null)
                .monto(transaccion.getMonto())
                .descripcion(transaccion.getDescripcion())
                .claveIdempotencia(transaccion.getClaveIdempotencia())
                .build();
    }
}