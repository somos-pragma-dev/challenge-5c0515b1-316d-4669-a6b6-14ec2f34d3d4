package com.banco.api.controller;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.dto.ErrorResponseDTO;
import com.banco.api.dto.TransferenciaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.model.Cuenta;
import com.banco.api.service.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cuentas")
@Tag(name = "Cuentas", description = "Operaciones relacionadas con cuentas bancarias")
public class CuentaController {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{numeroCuenta}/saldo")
    @Operation(summary = "Consultar saldo de cuenta", 
               description = "Retorna el saldo actual de una cuenta bancaria específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saldo consultado exitosamente",
                     content = @Content(schema = @Schema(implementation = CuentaDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada",
                     content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<CuentaDTO> consultarSaldo(
            @Parameter(description = "Número de cuenta bancaria", required = true, example = "1234567890")
            @PathVariable String numeroCuenta) {
        CuentaDTO cuenta = cuentaService.consultarSaldo(numeroCuenta);
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping("/transferir")
    @Operation(summary = "Realizar transferencia", 
               description = "Ejecuta una transferencia entre dos cuentas bancarias. " +
                            "La operación es idempotente cuando se proporciona una clave de idempotencia única.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transferencia realizada exitosamente",
                     content = @Content(schema = @Schema(implementation = TransferenciaDTO.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida o clave de idempotencia inválida",
                     content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada",
                     content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
        @ApiResponse(responseCode = "422", description = "Saldo insuficiente",
                     content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<TransferenciaDTO> realizarTransferencia(
            @Parameter(description = "Número de cuenta de origen", required = true, example = "1234567890")
            @RequestParam String cuentaOrigen,
            
            @Parameter(description = "Número de cuenta de destino", required = true, example = "0987654321")
            @RequestParam String cuentaDestino,
            
            @Parameter(description = "Monto a transferir", required = true, example = "1000.00")
            @RequestParam BigDecimal monto,
            
            @Parameter(description = "Clave única para idempotencia (UUID recomendado)", 
                       required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @RequestHeader("X-Idempotency-Key") String claveIdempotencia,
            
            @Parameter(description = "Descripción opcional de la transferencia")
            @RequestParam(required = false) String descripcion) {
        
        TransferenciaDTO transferencia = cuentaService.realizarTransferencia(
                cuentaOrigen, cuentaDestino, monto, claveIdempotencia, descripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferencia);
    }

    @GetMapping("/{numeroCuenta}/existe")
    @Operation(summary = "Verificar existencia de cuenta", 
               description = "Indica si una cuenta bancaria existe en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Map<String, Boolean>> verificarCuenta(
            @Parameter(description = "Número de cuenta bancaria", required = true)
            @PathVariable String numeroCuenta) {
        boolean existe = cuentaService.cuentaExiste(numeroCuenta);
        return ResponseEntity.ok(Map.of("existe", existe));
    }

    @GetMapping("/{numeroCuenta}/activa")
    @Operation(summary = "Verificar estado de cuenta", 
               description = "Indica si una cuenta bancaria está activa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Map<String, Boolean>> verificarCuentaActiva(
            @Parameter(description = "Número de cuenta bancaria", required = true)
            @PathVariable String numeroCuenta) {
        if (!cuentaService.cuentaExiste(numeroCuenta)) {
            throw new CuentaNoEncontradaException(numeroCuenta);
        }
        boolean activa = cuentaService.cuentaActiva(numeroCuenta);
        return ResponseEntity.ok(Map.of("activa", activa));
    }
}