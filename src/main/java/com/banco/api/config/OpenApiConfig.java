package com.banco.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Bancaria - Gestión de Cuentas y Transferencias")
                        .version("1.0.0")
                        .description("""
                                API REST para la gestión de cuentas bancarias y transferencias.
                                
                                Esta API permite:
                                - Consultar saldo de cuentas
                                - Realizar transferencias entre cuentas
                                - Consultar historial de transacciones
                                - Gestionar cuentas de clientes
                                
                                La API implementa idempotencia mediante el header 'Idempotency-Key' para operaciones de transferencia.
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@banco.com")
                                .url("https://www.banco.com"))
                        .license(new License()
                                .name("Licencia Proprietaria")
                                .url("https://www.banco.com/licencia")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT para autenticación")));
    }
}