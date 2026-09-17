package com.banco.api.exception;

import com.banco.api.dto.ErrorResponseDTO;
import com.banco.api.model.Cuenta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(CuentaNoEncontradaException.class)
    public ResponseEntity<ErrorResponseDTO> handleCuentaNoEncontrada(
            CuentaNoEncontradaException ex, WebRequest request) {
        
        log.warn("Cuenta no encontrada: {}", ex.getMensajeTecnico());
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
            .codigoError(ex.getCodigoError())
            .mensaje(ex.getMessage())
            .detalles(buildDetalles(request, "cuenta", ex.getNumeroCuenta()))
            .timestamp(LocalDateTime.now())
            .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ErrorResponseDTO> handleSaldoInsuficiente(
            SaldoInsuficienteException ex, WebRequest request) {
        
        log.warn("Saldo insuficiente: {}", ex.getMensajeTecnico());
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
            .codigoError(ex.getCodigoError())
            .mensaje(ex.getMessage())
            .detalles(buildDetalles(request, "saldo_actual", ex.getSaldoActual().toString()))
            .timestamp(LocalDateTime.now())
            .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        
        log.warn("Argumento ilegal: {}", ex.getMessage());
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
            .codigoError("ARGUMENTO_INVALIDO")
            .mensaje(ex.getMessage())
            .detalles(buildDetalles(request, null, null))
            .timestamp(LocalDateTime.now())
            .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        
        String mensaje = String.format("El parámetro '%s' recibió un valor inválido: '%s'", 
            ex.getName(), ex.getValue());
        
        log.warn("Tipo de argumento inválido: {}", mensaje);
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
            .codigoError("TIPO_ARGUMENTO_INVALIDO")
            .mensaje(mensaje)
            .detalles(buildDetalles(request, "parametro", ex.getName()))
            .timestamp(LocalDateTime.now())
            .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception ex, WebRequest request) {
        
        log.error("Error inesperado: ", ex);
        
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
            .codigoError("ERROR_INTERNO")
            .mensaje("Ha ocurrido un error interno. Por favor, contacte al administrador.")
            .detalles(buildDetalles(request, null, null))
            .timestamp(LocalDateTime.now())
            .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    
    private Map<String, String> buildDetalles(WebRequest request, String clave, String valor) {
        Map<String, String> detalles = new HashMap<>();
        detalles.put("path", request.getDescription(false).replace("uri=", ""));
        detalles.put("metodo", request.getMethod());
        
        if (clave != null && valor != null) {
            detalles.put(clave, valor);
        }
        
        return detalles;
    }
}