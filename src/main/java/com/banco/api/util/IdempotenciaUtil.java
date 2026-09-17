package com.banco.api.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IdempotenciaUtil {
    private static final int MAX_ENTRADAS = 10000;
    private final ConcurrentHashMap<String, EntradaIdempotencia> cache = new ConcurrentHashMap<>();

    public IdempotenciaUtil() {}

    public boolean esRequisicionIdempotente(String claveIdempotencia) {
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            return true;
        }
        return !cache.containsKey(claveIdempotencia);
    }

    public void registrarRequisicion(String claveIdempotencia, ResultadoOperacion resultado) {
        if (claveIdempotencia != null && !claveIdempotencia.isBlank()) {
            if (cache.size() >= MAX_ENTRADAS) {
                cache.clear();
            }
            cache.put(claveIdempotencia, new EntradaIdempotencia(resultado, LocalDateTime.now()));
        }
    }

    public record ResultadoOperacion(boolean exitoso, String mensaje) {}

    private record EntradaIdempotencia(ResultadoOperacion resultado, LocalDateTime timestamp) {}
}