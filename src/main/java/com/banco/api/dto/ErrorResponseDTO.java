package com.banco.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Schema(description = "DTO para estandarizar respuestas de error en la API")
public record ErrorResponseDTO(
    @Schema(description = "Código de error HTTP", example = "400")
    int codigo,
    
    @Schema(description = "Mensaje principal del error", example = "Saldo insuficiente para realizar la transferencia")
    String mensaje,
    
    @Schema(description = "Ruta del endpoint que generó el error", example = "/api/cuentas/transferir")
    String ruta,
    
    @Schema(description = "Marca de tiempo del error")
    LocalDateTime timestamp,
    
    @Schema(description = "Detalles adicionales del error (opcional)")
    List<String> detalles,
    
    @Schema(description = "Errores de validación por campo")
    Map<String, String> erroresCampo
) {
    public static ErrorResponseDTO crear(int codigo, String mensaje, String ruta) {
        return new ErrorResponseDTO(
            codigo,
            mensaje,
            ruta,
            LocalDateTime.now(),
            null,
            null
        );
    }
    
    public static ErrorResponseDTO crear(int codigo, String mensaje, String ruta, String detalle) {
        return new ErrorResponseDTO(
            codigo,
            mensaje,
            ruta,
            LocalDateTime.now(),
            List.of(detalle),
            null
        );
    }
    
    public static ErrorResponseDTO crear(int codigo, String mensaje, String ruta, 
                                         Map<String, String> erroresCampo) {
        return new ErrorResponseDTO(
            codigo,
            mensaje,
            ruta,
            LocalDateTime.now(),
            null,
            erroresCampo
        );
    }
    
    public static ErrorResponseDTO crear(int codigo, String mensaje, String ruta, 
                                         List<String> detalles) {
        return new ErrorResponseDTO(
            codigo,
            mensaje,
            ruta,
            LocalDateTime.now(),
            detalles,
            null
        );
    }
}