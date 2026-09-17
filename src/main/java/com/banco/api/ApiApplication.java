package com.banco.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase principal de la aplicación Spring Boot.
 * Punto de entrada que inicia el contexto de Spring y configura la aplicación.
 * Esta clase contiene la configuración core del proyecto bancario.
 */
@SpringBootApplication
public class ApiApplication {
    
    /**
     * Método principal que arranca la aplicación Spring Boot.
     * Utiliza SpringApplication.run() para iniciar el contenedor de IoC
     * y desplegar la aplicación web embebida.
     * 
     * @param args argumentos de línea de comandos pasados al proceso
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
    
    /**
     * Configuración global de CORS para permitir solicitudes desde cualquier origen.
     * En un entorno de producción, esto debería restringirse a los dominios conocidos.
     * Esta configuración permite que los clientes frontend consuman la API sin restricciones.
     * 
     * @return WebMvcConfigurer con la configuración de CORS aplicada
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}