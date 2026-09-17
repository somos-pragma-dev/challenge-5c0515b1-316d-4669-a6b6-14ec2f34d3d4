# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/banco/api/service/CuentaService.java` — `ResultadoOperacion`: ResultadoOperacion se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.banco.api.util.ResultadoOperacion.
- `src/test/java/com/banco/api/controller/CuentaControllerTest.java` — `CuentaNoEncontradaException`: CuentaNoEncontradaException se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.banco.api.exception.CuentaNoEncontradaException.
- `src/test/java/com/banco/api/controller/CuentaControllerTest.java` — `SaldoInsuficienteException`: SaldoInsuficienteException se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.banco.api.exception.SaldoInsuficienteException.
- `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Cuenta`: El import com.banco.api.model.Cuenta no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `src/main/java/com/banco/api/dto/ErrorResponseDTO.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/api/config/OpenApiConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/api/controller/CuentaController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getCuentaOrigen`: Se invoca `getCuentaOrigen` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getCuentaDestino`: Se invoca `getCuentaDestino` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getMonto`: Se invoca `getMonto` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getDescripcion`: Se invoca `getDescripcion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getClaveIdempotencia`: Se invoca `getClaveIdempotencia` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/api/service/CuentaService.java` — `CuentaRepository.findById`: Se invoca `findById` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/api/service/CuentaService.java` — `CuentaRepository.findAll`: Se invoca `findAll` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banco/api/service/CuentaService.java` — `CuentaRepository.save`: Se invoca `save` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaRepository.findById`: Se invoca `findById` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.id`: Se invoca `id` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.numeroCuenta`: Se invoca `numeroCuenta` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.saldo`: Se invoca `saldo` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.tipoCuenta`: Se invoca `tipoCuenta` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaRepository.findAll`: Se invoca `findAll` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.size`: Se invoca `size` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaRepository.save`: Se invoca `save` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Crear una API REST con Spring Boot, JPA y documentación OpenAPI

### Reto
- Tema: Java Spring Boot
- Seniority: junior-l1
- Tipo: practical
- Título: Desarrollo de una API REST en dominio de banca
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Diseño del modelo de dominio — objetivo: Definir las entidades y relaciones necesarias para el dominio de banca. — entregable (NO resolver): Modelo de dominio documentado con entidades y relaciones.
- Fase 2: Implementación de la API REST — objetivo: Crear las endpoints para consultar saldo y realizar transferencias. — entregable (NO resolver): API REST con endpoints funcionales y manejo de errores.
- Fase 3: Documentación de la API con OpenAPI — objetivo: Documentar la API utilizando OpenAPI. — entregable (NO resolver): Documentación de la API con OpenAPI.
- Fase 4: Pruebas y optimización — objetivo: Realizar pruebas unitarias y de integración, y optimizar la API para manejar altas cargas. — entregable (NO resolver): API REST con pruebas unitarias y de integración, y optimizada para altas cargas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>
    
    <groupId>com.banco</groupId>
    <artifactId>api-bancaria</artifactId>
    <version>1.0.0</version>
    <name>api-bancaria</name>
    <description>API REST para gestión de cuentas bancarias y transferencias</description>
    
    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <springdoc.version>2.6.0</springdoc.version>
        <lombok.version>1.18.34</lombok.version>
        <h2.version>2.3.230</h2.version>
        <mockito.version>5.12.0</mockito.version>
    </properties>
    
    <dependencies>
        <!-- Spring Boot Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <!-- Spring Boot Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <!-- Lombok para reducción de boilerplate -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- OpenAPI/Swagger Documentation -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc.version}</version>
        </dependency>
        
        <!-- Base de datos H2 en memoria -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>${h2.version}</version>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Spring Boot Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!-- Mockito para tests -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>${mockito.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <!-- Plugin de compilación Maven -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            
            <!-- Plugin de Spring Boot -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/banco/api/ApiApplication.java ===
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

// === ARCHIVO: src/main/resources/application.properties ===
# Configuración del servidor embebido
server.port=8080
server.servlet.context-path=/api/v1

# Configuración de la aplicación
spring.application.name=api-bancaria
spring.profiles.active=default

# Configuración del DataSource H2 en memoria
spring.datasource.url=jdbc:h2:mem:bancoDB
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración de JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

# Configuración de H2 Console (solo para desarrollo)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true

# Configuración de OpenAPI/Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.api-docs.enabled=true

# Configuración de logging
logging.level.root=INFO
logging.level.com.banco.api=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

# Configuración de internacionalización
spring.messages.basename=messages
spring.messages.encoding=UTF-8

# Configuración de manejo de errores
server.error.include-message=always
server.error.include-binding-errors=always
server.error.include-stacktrace=never
server.error.include-exception=false

# Configuración de compression
server.compression.enabled=true
server.compression.mime-types=application/json,application/xml,text/html,text/xml,text/plain

# Configuración de timeouts
server.connection.timeout=20000
server.servlet.session.timeout=30m

// === ARCHIVO: src/main/java/com/banco/api/model/Cuenta.java ===
package com.banco.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una cuenta bancaria en el sistema.
 * Cada cuenta pertenece a un cliente y puede tener múltiples transacciones.
 */
@Entity
@Table(name = "cuentas", indexes = {
    @Index(name = "idx_numero_cuenta", columnList = "numero_cuenta", unique = true),
    @Index(name = "idx_cliente_id", columnList = "cliente_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 20)
    private String numeroCuenta;

    @Column(name = "saldo", nullable = false, precision = 19, scale = 4)
    @Builder.Default
    private BigDecimal saldo = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false, length = 20)
    private TipoCuenta tipoCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoCuenta estado = EstadoCuenta.ACTIVA;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaOrigen", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Transaccion> transaccionesOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "cuentaDestino", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Transaccion> transaccionesDestino = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (estado == null) {
            estado = EstadoCuenta.ACTIVA;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Verifica si la cuenta tiene saldo suficiente para una operación.
     */
    public boolean tieneSaldoSuficiente(BigDecimal monto) {
        return saldo.compareTo(monto) >= 0;
    }

    /**
     * Realiza el débito de la cuenta por el monto especificado.
     */
    public void debitar(BigDecimal monto) {
        if (!tieneSaldoSuficiente(monto)) {
            throw new IllegalStateException("Saldo insuficiente para debitar");
        }
        this.saldo = saldo.subtract(monto);
    }

    /**
     * Realiza el crédito de la cuenta por el monto especificado.
     */
    public void acreditar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser positivo");
        }
        this.saldo = saldo.add(monto);
    }

    /**
     * Tipos de cuenta bancaria soportados.
     */
    public enum TipoCuenta {
        AHORROS,
        CORRIENTE,
        PLAZO_FIJO
    }

    /**
     * Estados posibles de una cuenta.
     */
    public enum EstadoCuenta {
        ACTIVA,
        INACTIVA,
        BLOQUEADA,
        CERRADA
    }
}

// === ARCHIVO: src/main/java/com/banco/api/model/Cliente.java ===
package com.banco.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un cliente del banco.
 * Un cliente puede tener múltiples cuentas bancarias asociadas.
 */
@Entity
@Table(name = "clientes", indexes = {
    @Index(name = "idx_identificacion", columnList = "identificacion", unique = true),
    @Index(name = "idx_email", columnList = "email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion", nullable = false, unique = true, length = 20)
    private String identificacion;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion", nullable = false, length = 10)
    private TipoIdentificacion tipoIdentificacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoCliente estado = EstadoCliente.ACTIVO;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Cuenta> cuentas = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (estado == null) {
            estado = EstadoCliente.ACTIVO;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Obtiene el nombre completo del cliente.
     */
    public String getNombreCompleto() {
        return nombre + (apellido != null ? " " + apellido : "");
    }

    /**
     * Verifica si el cliente está activo para realizar operaciones.
     */
    public boolean isActivo() {
        return EstadoCliente.ACTIVO.equals(estado);
    }

    /**
     * Agrega una cuenta al cliente.
     */
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setCliente(this);
    }

    /**
     * Tipos de identificación soportados.
     */
    public enum TipoIdentificacion {
        CEDULA,
        NIT,
        PASAPORTE,
        IDENTIFICACION_EXTRANJERO
    }

    /**
     * Estados posibles de un cliente.
     */
    public enum EstadoCliente {
        ACTIVO,
        INACTIVO,
        SUSPENDIDO,
        ELIMINADO
    }
}

// === ARCHIVO: src/main/java/com/banco/api/model/Transaccion.java ===
package com.banco.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa una transacción bancaria (transferencia).
 * Registra tanto débitos como créditos entre cuentas.
 */
@Entity
@Table(name = "transacciones", indexes = {
    @Index(name = "idx_cuenta_origen", columnList = "cuenta_origen_id"),
    @Index(name = "idx_cuenta_destino", columnList = "cuenta_destino_id"),
    @Index(name = "idx_fecha", columnList = "fecha_transaccion"),
    @Index(name = "idx_clave_idempotencia", columnList = "clave_idempotencia", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_origen_id", nullable = false)
    private Cuenta cuentaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_destino_id", nullable = false)
    private Cuenta cuentaDestino;

    @Column(name = "monto", nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Column(name = "monto_comision", precision = 19, scale = 4)
    @Builder.Default
    private BigDecimal montoComision = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transaccion", nullable = false, length = 30)
    private TipoTransaccion tipoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoTransaccion estado = EstadoTransaccion.PENDIENTE;

    @Column(name = "fecha_transaccion", nullable = false, updatable = false)
    private LocalDateTime fechaTransaccion;

    @Column(name = "fecha_procesamiento")
    private LocalDateTime fechaProcesamiento;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "referencia_externa", length = 50)
    private String referenciaExternna;

    @Column(name = "clave_idempotencia", unique = true, length = 64)
    private String claveIdempotencia;

    @Column(name = "codigo_error", length = 20)
    private String codigoError;

    @Column(name = "mensaje_error", length = 500)
    private String mensajeError;

    @PrePersist
    protected void onCreate() {
        fechaTransaccion = LocalDateTime.now();
        if (estado == null) {
            estado = EstadoTransaccion.PENDIENTE;
        }
    }

    /**
     * Procesa la transacción moviendo el monto entre cuentas.
     */
    public void procesar() {
        if (estado != EstadoTransaccion.PENDIENTE) {
            throw new IllegalStateException("La transacción no está en estado pendiente");
        }

        if (!cuentaOrigen.tieneSaldoSuficiente(monto.add(montoComision))) {
            throw new IllegalStateException("Saldo insuficiente en cuenta origen");
        }

        cuentaOrigen.debitar(monto.add(montoComision));
        cuentaDestino.acreditar(monto);

        this.estado = EstadoTransaccion.COMPLETADA;
        this.fechaProcesamiento = LocalDateTime.now();
    }

    /**
     * Revierte la transacción en caso de error.
     */
    public void revertir(String motivo) {
        if (estado != EstadoTransaccion.PENDIENTE && estado != EstadoTransaccion.COMPLETADA) {
            throw new IllegalStateException("La transacción no puede ser revertida");
        }

        this.estado = EstadoTransaccion.REVERTIDA;
        this.fechaProcesamiento = LocalDateTime.now();
        this.mensajeError = motivo;
    }

    /**
     * Fallida la transacción con el código de error especificado.
     */
    public void fallar(String codigoError, String mensajeError) {
        this.estado = EstadoTransaccion.FALLIDA;
        this.fechaProcesamiento = LocalDateTime.now();
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
    }

    /**
     * Tipos de transacción bancaria.
     */
    public enum TipoTransaccion {
        TRANSFERENCIA,
        DEPOSITO,
        RETIRO,
        PAGO_SERVICIO,
        COMPRA
    }

    /**
     * Estados posibles de una transacción.
     */
    public enum EstadoTransaccion {
        PENDIENTE,
        COMPLETADA,
        FALLIDA,
        REVERTIDA,
        CANCELADA
    }
}

// === ARCHIVO: src/main/java/com/banco/api/dto/CuentaDTO.java ===
package com.banco.api.dto;

import com.banco.api.model.Cuenta;
import com.banco.api.model.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO para transferir datos de cuentas entre capas (consulta de saldo)")
public record CuentaDTO(
    @Schema(description = "Identificador único de la cuenta", example = "1")
    Long id,
    
    @Schema(description = "Número de cuenta bancaria", example = "1234567890")
    String numeroCuenta,
    
    @Schema(description = "Saldo actual de la cuenta", example = "10000.00")
    BigDecimal saldo,
    
    @Schema(description = "Tipo de cuenta (AHORROS, CORRIENTE)")
    Cuenta.TipoCuenta tipoCuenta,
    
    @Schema(description = "Estado de la cuenta (ACTIVA, INACTIVA, BLOQUEADA)")
    Cuenta.EstadoCuenta estado,
    
    @Schema(description = "Nombre completo del titular de la cuenta", example = "Juan Pérez García")
    String nombreTitular,
    
    @Schema(description = "Identificación del titular", example = "12345678")
    String identificacionTitular,
    
    @Schema(description = "Fecha de última actualización del saldo")
    LocalDateTime fechaActualizacion
) {
    public static CuentaDTO fromEntity(Cuenta cuenta) {
        Cliente cliente = cuenta.cliente();
        String nombreCompleto = cliente != null ? cliente.getNombreCompleto() : "Sin titular";
        String identificacion = cliente != null ? cliente.getIdentificacion() : "N/A";
        
        return new CuentaDTO(
            cuenta.getId(),
            cuenta.getNumeroCuenta(),
            cuenta.getSaldo(),
            cuenta.getTipoCuenta(),
            cuenta.getEstado(),
            nombreCompleto,
            identificacion,
            cuenta.getFechaActualizacion()
        );
    }
}

// === ARCHIVO: src/main/java/com/banco/api/dto/TransferenciaDTO.java ===
package com.banco.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "DTO para recibir datos de transferencias desde el cliente")
public class TransferenciaDTO {
    
    @NotBlank(message = "El número de cuenta de origen es obligatorio")
    @Schema(description = "Número de cuenta de origen", example = "1234567890", required = true)
    private String cuentaOrigen;
    
    @NotBlank(message = "El número de cuenta de destino es obligatorio")
    @Schema(description = "Número de cuenta de destino", example = "0987654321", required = true)
    private String cuentaDestino;
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    @Schema(description = "Monto a transferir", example = "500.00", required = true)
    private BigDecimal monto;
    
    @Schema(description = "Descripción opcional de la transferencia", example = "Pago de servicios")
    private String descripcion;
    
    @Schema(description = "Clave de idempotencia para evitar transferencias duplicadas", 
            example = "uuid-unico-por-solicitud")
    private String claveIdempotencia;

    public TransferenciaDTO() {
    }

    public TransferenciaDTO(String cuentaOrigen, String cuentaDestino, BigDecimal monto, 
                           String descripcion, String claveIdempotencia) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.descripcion = descripcion;
        this.claveIdempotencia = claveIdempotencia;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClaveIdempotencia() {
        return claveIdempotencia;
    }

    public void setClaveIdempotencia(String claveIdempotencia) {
        this.claveIdempotencia = claveIdempotencia;
    }
}

// === ARCHIVO: src/main/java/com/banco/api/dto/ErrorResponseDTO.java ===
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

// === ARCHIVO: src/main/java/com/banco/api/repository/CuentaRepository.java ===
package com.banco.api.repository;

import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.model.Cuenta.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    List<Cuenta> findByClienteId(Long clienteId);

    List<Cuenta> findByTipoCuenta(TipoCuenta tipoCuenta);

    List<Cuenta> findByEstado(EstadoCuenta estado);

    @Query("SELECT c FROM Cuenta c WHERE c.cliente.id = :clienteId AND c.estado = :estado")
    List<Cuenta> findByClienteIdAndEstado(@Param("clienteId") Long clienteId, @Param("estado") EstadoCuenta estado);

    @Query("SELECT c FROM Cuenta c WHERE c.saldo >= :saldoMinimo AND c.estado = 'ACTIVA'")
    List<Cuenta> findCuentasConSaldoMinimo(@Param("saldoMinimo") BigDecimal saldoMinimo);

    @Query("SELECT c FROM Cuenta c WHERE c.fechaCreacion BETWEEN :fechaInicio AND :fechaFin")
    List<Cuenta> findByFechaCreacionBetween(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT COUNT(c) FROM Cuenta c WHERE c.cliente.id = :clienteId")
    Long countByClienteId(@Param("clienteId") Long clienteId);

    @Query("SELECT SUM(c.saldo) FROM Cuenta c WHERE c.cliente.id = :clienteId AND c.estado = 'ACTIVA'")
    BigDecimal getSaldoTotalByClienteId(@Param("clienteId") Long clienteId);

    boolean existsByNumeroCuenta(String numeroCuenta);

    @Query("SELECT c FROM Cuenta c JOIN FETCH c.cliente WHERE c.id = :id")
    Optional<Cuenta> findByIdWithCliente(@Param("id") Long id);

    @Query("SELECT c FROM Cuenta c LEFT JOIN FETCH c.transaccionesOrigen LEFT JOIN FETCH c.transaccionesDestino WHERE c.id = :id")
    Optional<Cuenta> findByIdWithTransacciones(@Param("id") Long id);

    @Query("SELECT c FROM Cuenta c WHERE c.numeroCuenta LIKE %:prefix% ORDER BY c.numeroCuenta ASC")
    List<Cuenta> findByNumeroCuentaStartingWith(@Param("prefix") String prefix);
}

// === ARCHIVO: src/main/java/com/banco/api/repository/TransaccionRepository.java ===
package com.banco.api.repository;

import com.banco.api.model.Transaccion;
import com.banco.api.model.Transaccion.EstadoTransaccion;
import com.banco.api.model.Transaccion.TipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByCuentaOrigenId(Long cuentaOrigenId);

    List<Transaccion> findByCuentaDestinoId(Long cuentaDestinoId);

    List<Transaccion> findByCuentaOrigenIdOrCuentaDestinoId(Long cuentaOrigenId, Long cuentaDestinoId);

    List<Transaccion> findByEstado(EstadoTransaccion estado);

    List<Transaccion> findByTipoTransaccion(TipoTransaccion tipoTransaccion);

    Optional<Transaccion> findByClaveIdempotencia(String claveIdempotencia);

    @Query("SELECT t FROM Transaccion t WHERE t.cuentaOrigen.id = :cuentaId OR t.cuentaDestino.id = :cuentaId ORDER BY t.fechaTransaccion DESC")
    List<Transaccion> findByCuentaId(@Param("cuentaId") Long cuentaId);

    @Query("SELECT t FROM Transaccion t WHERE t.fechaTransaccion BETWEEN :fechaInicio AND :fechaFin")
    List<Transaccion> findByFechaTransaccionBetween(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT t FROM Transaccion t WHERE t.cuentaOrigen.id = :cuentaId AND t.fechaTransaccion BETWEEN :fechaInicio AND :fechaFin")
    List<Transaccion> findByCuentaOrigenIdAndFechaBetween(@Param("cuentaId") Long cuentaId, @Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT t FROM Transaccion t WHERE t.monto >= :montoMinimo AND t.monto <= :montoMaximo")
    List<Transaccion> findByMontoBetween(@Param("montoMinimo") BigDecimal montoMinimo, @Param("montoMaximo") BigDecimal montoMaximo);

    @Query("SELECT SUM(t.monto) FROM Transaccion t WHERE t.cuentaOrigen.id = :cuentaId AND t.estado = 'COMPLETADA'")
    BigDecimal getMontoTotalEnviado(@Param("cuentaId") Long cuentaId);

    @Query("SELECT SUM(t.monto) FROM Transaccion t WHERE t.cuentaDestino.id = :cuentaId AND t.estado = 'COMPLETADA'")
    BigDecimal getMontoTotalRecibido(@Param("cuentaId") Long cuentaId);

    @Query("SELECT COUNT(t) FROM Transaccion t WHERE t.cuentaOrigen.id = :cuentaId OR t.cuentaDestino.id = :cuentaId")
    Long countByCuentaId(@Param("cuentaId") Long cuentaId);

    @Query("SELECT t FROM Transaccion t WHERE t.estado = 'PENDIENTE' AND t.fechaTransaccion < :fechaLimite")
    List<Transaccion> findTransaccionesPendientesAntiguas(@Param("fechaLimite") LocalDateTime fechaLimite);

    @Query("SELECT t FROM Transaccion t JOIN FETCH t.cuentaOrigen JOIN FETCH t.cuentaDestino WHERE t.id = :id")
    Optional<Transaccion> findByIdWithCuentas(@Param("id") Long id);

    boolean existsByClaveIdempotencia(String claveIdempotencia);

    @Query("SELECT t FROM Transaccion t WHERE t.referenciaExternna = :referencia AND t.cuentaOrigen.id = :cuentaOrigenId")
    Optional<Transaccion> findByReferenciaAndCuentaOrigen(@Param("referencia") String referencia, @Param("cuentaOrigenId") Long cuentaOrigenId);
}

// === ARCHIVO: src/main/java/com/banco/api/config/OpenApiConfig.java ===
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

// === ARCHIVO: src/main/java/com/banco/api/exception/SaldoInsuficienteException.java ===
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

// === ARCHIVO: src/main/java/com/banco/api/service/CuentaService.java ===
package com.banco.api.service;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.dto.TransferenciaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.exception.SaldoInsuficienteException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.EstadoTransaccion;
import com.banco.api.model.TipoTransaccion;
import com.banco.api.model.Transaccion;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.TransaccionRepository;
import com.banco.api.util.IdempotenciaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;
    private final IdempotenciaUtil idempotenciaUtil;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository, 
                         TransaccionRepository transaccionRepository,
                         IdempotenciaUtil idempotenciaUtil) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
        this.idempotenciaUtil = idempotenciaUtil;
    }

    public CuentaDTO consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
        return CuentaDTO.fromEntity(cuenta);
    }

    @Transactional
    public TransferenciaDTO realizarTransferencia(String numeroCuentaOrigen, 
                                                   String numeroCuentaDestino, 
                                                   BigDecimal monto,
                                                   String claveIdempotencia,
                                                   String descripcion) {
        if (!idempotenciaUtil.esClaveValida(claveIdempotencia)) {
            throw new IllegalArgumentException("Clave de idempotencia inválida o vacía");
        }

        Optional<Transaccion> transaccionExistente = transaccionRepository
                .findByClaveIdempotencia(claveIdempotencia);
        if (transaccionExistente.isPresent()) {
            return TransferenciaDTO.fromEntity(transaccionExistente.get());
        }

        Cuenta cuentaOrigen = cuentaRepository.findByNumeroCuenta(numeroCuentaOrigen)
                .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuentaOrigen));

        Cuenta cuentaDestino = cuentaRepository.findByNumeroCuenta(numeroCuentaDestino)
                .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuentaDestino));

        if (!cuentaOrigen.tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException(
                    cuentaOrigen.getNumeroCuenta(), 
                    cuentaOrigen.getSaldo(), 
                    monto
            );
        }

        if (!cuentaOrigen.isActivo() || !cuentaDestino.isActivo()) {
            throw new IllegalStateException("Una o ambas cuentas no están activas para realizar transferencias");
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaOrigen(cuentaOrigen);
        transaccion.setCuentaDestino(cuentaDestino);
        transaccion.setMonto(monto);
        transaccion.setMontoComision(calcularComision(monto));
        transaccion.setTipoTransaccion(TipoTransaccion.TRANSFERENCIA);
        transaccion.setEstadoTransaccion(EstadoTransaccion.PENDIENTE);
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setDescripcion(descripcion != null ? descripcion : 
                String.format("Transferencia de %s a %s", numeroCuentaOrigen, numeroCuentaDestino));
        transaccion.setClaveIdempotencia(claveIdempotencia);
        transaccion.setReferenciaExternna(UUID.randomUUID().toString());

        cuentaOrigen.debitar(monto.add(transaccion.getMontoComision()));
        cuentaDestino.acreditar(monto);

        transaccion.setEstadoTransaccion(EstadoTransaccion.EXITOSA);
        transaccion.setFechaProcesamiento(LocalDateTime.now());

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);
        transaccionRepository.save(transaccion);

        return TransferenciaDTO.fromEntity(transaccion);
    }

    private BigDecimal calcularComision(BigDecimal monto) {
        BigDecimal tasaComision = new BigDecimal("0.01");
        return monto.multiply(tasaComision).setScale(2, java.math.RoundingMode.HALF_UP);
    }

    public boolean cuentaExiste(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta).isPresent();
    }

    public boolean cuentaActiva(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .map(Cuenta::isActivo)
                .orElse(false);
    }
}

// === ARCHIVO: src/main/java/com/banco/api/controller/CuentaController.java ===
package com.banco.api.controller;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.dto.ErrorResponseDTO;
import com.banco.api.dto.TransferenciaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.exception.SaldoInsuficienteException;
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

// === ARCHIVO: src/main/java/com/banco/api/exception/CuentaNoEncontradaException.java ===
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

// === ARCHIVO: src/main/java/com/banco/api/exception/GlobalExceptionHandler.java ===
package com.banco.api.exception;

import com.banco.api.dto.ErrorResponseDTO;
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

// === ARCHIVO: src/main/java/com/banco/api/util/IdempotenciaUtil.java ===
package com.banco.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class IdempotenciaUtil {
    
    private static final long TTL_MILLISEGUNDOS = 3600000; // 1 hora
    private static final int MAX_ENTRADAS = 10000;
    
    private final ConcurrentHashMap<String, EntradaIdempotencia> cache;
    
    public IdempotenciaUtil() {
        this.cache = new ConcurrentHashMap<>();
        iniciarLimpiezaAutomatica();
    }
    
    public boolean esRequisicionIdempotente(String claveIdempotencia) {
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            return false;
        }
        
        EntradaIdempotencia entrada = cache.get(claveIdempotencia);
        
        if (entrada == null) {
            return false;
        }
        
        if (estaExpirada(entrada)) {
            cache.remove(claveIdempotencia);
            log.debug("Entrada idempotente expirada para clave: {}", claveIdempotencia);
            return false;
        }
        
        log.info("Requisición idempotente detectada para clave: {}", claveIdempotencia);
        return true;
    }
    
    public void registrarRequisicion(String claveIdempotencia, ResultadoOperacion resultado) {
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            log.warn("Intento de registrar clave de idempotencia nula o vacía");
            return;
        }
        
        if (cache.size() >= MAX_ENTRADAS) {
            limpiarEntradasExpiradas();
            
            if (cache.size() >= MAX_ENTRADAS) {
                log.warn("Cache de idempotencia lleno, no se puede registrar nueva entrada");
                return;
            }
        }
        
        EntradaIdempotencia entrada = new EntradaIdempotencia(
            claveIdempotencia, 
            resultado, 
            Instant.now().toEpochMilli()
        );
        
        cache.put(claveIdempotencia, entrada);
        log.debug("Registrada operación para clave de idempotencia: {}", claveIdempotencia);
    }
    
    public Optional<Resulta

// === ARCHIVO: src/test/java/com/banco/api/controller/CuentaControllerTest.java ===
package com.banco.api.controller;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.model.Cuenta.TipoCuenta;
import com.banco.api.service.CuentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Test
    void testObtenerCuentaPorId_CuentaExiste_Retorna200() throws Exception {
        CuentaDTO cuentaDTO = new CuentaDTO(
            1L,
            "1234567890",
            new BigDecimal("1000.00"),
            TipoCuenta.AHORROS.name(),
            EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            1L,
            "Juan",
            "Perez",
            "juan.perez@banco.com"
        );

        when(cuentaService.obtenerCuentaPorId(1L)).thenReturn(cuentaDTO);

        mockMvc.perform(get("/api/cuentas/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.numeroCuenta").value("1234567890"))
            .andExpect(jsonPath("$.saldo").value(1000.00))
            .andExpect(jsonPath("$.tipoCuenta").value("AHORROS"));
    }

    @Test
    void testObtenerCuentaPorId_CuentaNoExiste_Retorna404() throws Exception {
        when(cuentaService.obtenerCuentaPorId(999L))
            .thenThrow(new com.banco.api.exception.CuentaNoEncontradaException("Cuenta no encontrada"));

        mockMvc.perform(get("/api/cuentas/999")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testListarCuentas_RetornaListaDeCuentas_Retorna200() throws Exception {
        CuentaDTO cuenta1 = new CuentaDTO(
            1L, "1234567890", new BigDecimal("1000.00"),
            TipoCuenta.AHORROS.name(), EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(), LocalDateTime.now(), 1L, "Juan", "Perez", "juan@email.com"
        );
        CuentaDTO cuenta2 = new CuentaDTO(
            2L, "0987654321", new BigDecimal("2000.00"),
            TipoCuenta.CORRIENTE.name(), EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(), LocalDateTime.now(), 2L, "Maria", "Gomez", "maria@email.com"
        );

        when(cuentaService.listarCuentas()).thenReturn(List.of(cuenta1, cuenta2));

        mockMvc.perform(get("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].numeroCuenta").value("1234567890"))
            .andExpect(jsonPath("$[1].numeroCuenta").value("0987654321"));
    }

    @Test
    void testRealizarTransferencia_TransferenciaExitosa_Retorna200() throws Exception {
        CuentaDTO cuentaOrigen = new CuentaDTO(
            1L, "1234567890", new BigDecimal("1000.00"),
            TipoCuenta.AHORROS.name(), EstadoCuenta.ACTIVA.name(),
            LocalDateTime.now(), LocalDateTime.now(), 1L, "Juan", "Perez", "juan@email.com"
        );

        when(cuentaService.realizarTransferencia(eq(1L), eq(2L), any(BigDecimal.class), any()))
            .thenReturn(cuentaOrigen);

        String jsonRequest = "{\"cuentaOrigenId\":1,\"cuentaDestinoId\":2,\"monto\":500.00,\"descripcion\":\"Transferencia de prueba\"}";

        mockMvc.perform(post("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("X-Idempotency-Key", "unique-key-123"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.saldo").value(1000.00));
    }

    @Test
    void testRealizarTransferencia_SaldoInsuficiente_Retorna400() throws Exception {
        when(cuentaService.realizarTransferencia(eq(1L), eq(2L), any(BigDecimal.class), any()))
            .thenThrow(new com.banco.api.exception.SaldoInsuficienteException("Saldo insuficiente"));

        String jsonRequest = "{\"cuentaOrigenId\":1,\"cuentaDestinoId\":2,\"monto\":5000.00,\"descripcion\":\"Transferencia grande\"}";

        mockMvc.perform(post("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("X-Idempotency-Key", "unique-key-456"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testRealizarTransferencia_CuentaOrigenNoExiste_Retorna404() throws Exception {
        when(cuentaService.realizarTransferencia(eq(999L), eq(2L), any(BigDecimal.class), any()))
            .thenThrow(new com.banco.api.exception.CuentaNoEncontradaException("Cuenta origen no encontrada"));

        String jsonRequest = "{\"cuentaOrigenId\":999,\"cuentaDestinoId\":2,\"monto\":100.00,\"descripcion\":\"Transferencia\"}";

        mockMvc.perform(post("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .header("X-Idempotency-Key", "unique-key-789"))
            .andExpect(status().isNotFound());
    }
}

// === ARCHIVO: src/test/java/com/banco/api/service/CuentaServiceTest.java ===
package com.banco.api.service;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.exception.SaldoInsuficienteException;
import com.banco.api.model.Cliente;
import com.banco.api.model.Cliente.EstadoCliente;
import com.banco.api.model.Cliente.TipoIdentificacion;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.model.Cuenta.TipoCuenta;
import com.banco.api.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    private Cuenta cuentaMock;
    private Cliente clienteMock;

    @BeforeEach
    void setUp() {
        clienteMock = new Cliente();
        clienteMock.setId(1L);
        clienteMock.setIdentificacion("12345678");
        clienteMock.setNombre("Juan");
        clienteMock.setApellido("Perez");
        clienteMock.setEmail("juan.perez@banco.com");
        clienteMock.setTipoIdentificacion(TipoIdentificacion.CEDULA);
        clienteMock.setEstado(EstadoCliente.ACTIVO);
        clienteMock.setFechaCreacion(LocalDateTime.now());

        cuentaMock = new Cuenta();
        cuentaMock.setId(1L);
        cuentaMock.setNumeroCuenta("1234567890");
        cuentaMock.setSaldo(new BigDecimal("1000.00"));
        cuentaMock.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaMock.setEstado(EstadoCuenta.ACTIVA);
        cuentaMock.setCliente(clienteMock);
        cuentaMock.setFechaCreacion(LocalDateTime.now());
    }

    @Test
    void testObtenerCuentaPorId_CuentaExistente_RetornaCuentaDTO() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaMock));

        CuentaDTO resultado = cuentaService.obtenerCuentaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("1234567890", resultado.numeroCuenta());
        assertEquals(new BigDecimal("1000.00"), resultado.saldo());
        assertEquals("AHORROS", resultado.tipoCuenta());
        verify(cuentaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerCuentaPorId_CuentaNoExistente_LanzaExcepcion() {
        when(cuentaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CuentaNoEncontradaException.class, () -> {
            cuentaService.obtenerCuentaPorId(999L);
        });

        verify(cuentaRepository, times(1)).findById(999L);
    }

    @Test
    void testListarCuentas_RetornaListaDeCuentas() {
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setId(2L);
        cuenta2.setNumeroCuenta("0987654321");
        cuenta2.setSaldo(new BigDecimal("2000.00"));
        cuenta2.setTipoCuenta(TipoCuenta.CORRIENTE);
        cuenta2.setEstado(EstadoCuenta.ACTIVA);
        cuenta2.setCliente(clienteMock);

        when(cuentaRepository.findAll()).thenReturn(java.util.List.of(cuentaMock, cuenta2));

        java.util.List<CuentaDTO> resultado = cuentaService.listarCuentas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(cuentaRepository, times(1)).findAll();
    }

    @Test
    void testRealizarTransferencia_TransferenciaExitosa_DebitaYAcredita() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setNumeroCuenta("1234567890");
        cuentaOrigen.setSaldo(new BigDecimal("1000.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.ACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        Cuenta cuentaDestino = new Cuenta();
        cuentaDestino.setId(2L);
        cuentaDestino.setNumeroCuenta("0987654321");
        cuentaDestino.setSaldo(new BigDecimal("500.00"));
        cuentaDestino.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaDestino.setEstado(EstadoCuenta.ACTIVA);
        cuentaDestino.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaRepository.findById(2L)).thenReturn(Optional.of(cuentaDestino));
        when(cuentaRepository.save(any(Cuenta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String claveIdempotencia = UUID.randomUUID().toString();
        CuentaDTO resultado = cuentaService.realizarTransferencia(
            1L, 2L, new BigDecimal("300.00"), claveIdempotencia
        );

        assertNotNull(resultado);
        assertEquals(new BigDecimal("700.00"), cuentaOrigen.getSaldo());
        assertEquals(new BigDecimal("800.00"), cuentaDestino.getSaldo());
        verify(cuentaRepository, times(2)).findById(any());
        verify(cuentaRepository, times(2)).save(any(Cuenta.class));
    }

    @Test
    void testRealizarTransferencia_SaldoInsuficiente_LanzaExcepcion() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setNumeroCuenta("1234567890");
        cuentaOrigen.setSaldo(new BigDecimal("100.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.ACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaRepository.findById(2L)).thenReturn(Optional.of(cuentaMock));

        assertThrows(SaldoInsuficienteException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 2L, new BigDecimal("500.00"), UUID.randomUUID().toString()
            );
        });

        verify(cuentaRepository, times(1)).findById(1L);
        verify(cuentaRepository, never()).save(any(Cuenta.class));
    }

    @Test
    void testRealizarTransferencia_CuentaOrigenNoExiste_LanzaExcepcion() {
        when(cuentaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CuentaNoEncontradaException.class, () -> {
            cuentaService.realizarTransferencia(
                999L, 2L, new BigDecimal("100.00"), UUID.randomUUID().toString()
            );
        });

        verify(cuentaRepository, times(1)).findById(999L);
    }

    @Test
    void testRealizarTransferencia_CuentaDestinoNoExiste_LanzaExcepcion() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaMock));
        when(cuentaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(CuentaNoEncontradaException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 999L, new BigDecimal("100.00"), UUID.randomUUID().toString()
            );
        });

        verify(cuentaRepository, times(1)).findById(999L);
    }

    @Test
    void testRealizarTransferencia_MontoNegativo_LanzaExcepcion() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setSaldo(new BigDecimal("1000.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.ACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaRepository.findById(2L)).thenReturn(Optional.of(cuentaMock));

        assertThrows(IllegalArgumentException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 2L, new BigDecimal("-100.00"), UUID.randomUUID().toString()
            );
        });
    }

    @Test
    void testRealizarTransferencia_CuentaInactiva_LanzaExcepcion() {
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setId(1L);
        cuentaOrigen.setNumeroCuenta("1234567890");
        cuentaOrigen.setSaldo(new BigDecimal("1000.00"));
        cuentaOrigen.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaOrigen.setEstado(EstadoCuenta.INACTIVA);
        cuentaOrigen.setCliente(clienteMock);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuentaOrigen));

        assertThrows(IllegalStateException.class, () -> {
            cuentaService.realizarTransferencia(
                1L, 2L, new BigDecimal("100.00"), UUID.randomUUID().toString()
            );
        });
    }
}

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>
    
    <groupId>com.banco</groupId>
    <artifactId>api</artifactId>
    <version>1.0.0</version>
    <name>banco-api</name>
    <description>API Bancaria</description>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.3.4</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>3.3.4</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.6.0</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.3.230</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>3.3.4</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/banco/api/dto/CuentaDTO.java ===
package com.banco.api.dto;

import com.banco.api.model.Cuenta;
import com.banco.api.model.Cliente;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.model.Cuenta.TipoCuenta;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO para transferir datos de cuentas entre capas (consulta de saldo)")
public record CuentaDTO(
    @Schema(description = "Identificador único de la cuenta", example = "1")
    Long id,
    
    @Schema(description = "Número de cuenta bancaria", example = "1234567890")
    String numeroCuenta,
    
    @Schema(description = "Saldo actual de la cuenta", example = "10000.00")
    BigDecimal saldo,
    
    @Schema(description = "Tipo de cuenta (AHORROS, CORRIENTE)")
    TipoCuenta tipoCuenta,
    
    @Schema(description = "Estado de la cuenta (ACTIVA, INACTIVA, BLOQUEADA)")
    EstadoCuenta estado,
    
    @Schema(description = "Nombre completo del titular de la cuenta", example = "Juan Pérez García")
    String nombreTitular,
    
    @Schema(description = "Identificación del titular", example = "12345678")
    String identificacionTitular,
    
    @Schema(description = "Fecha de última actualización del saldo")
    LocalDateTime fechaActualizacion
) {
    public static CuentaDTO fromEntity(Cuenta cuenta) {
        Cliente cliente = cuenta.getCliente();
        String nombreCompleto = cliente != null ? cliente.getNombreCompleto() : "Sin titular";
        String identificacion = cliente != null ? cliente.getIdentificacion() : "N/A";
        
        return new CuentaDTO(
            cuenta.getId(),
            cuenta.getNumeroCuenta(),
            cuenta.getSaldo(),
            cuenta.getTipoCuenta(),
            cuenta.getEstado(),
            nombreCompleto,
            identificacion,
            cuenta.getFechaActualizacion()
        );
    }
}

// === ARCHIVO: src/main/java/com/banco/api/controller/CuentaController.java ===
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

// === ARCHIVO: src/main/java/com/banco/api/exception/GlobalExceptionHandler.java ===
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

// === ARCHIVO: src/main/java/com/banco/api/model/Cuenta.java ===
package com.banco.api.model;

import com.banco.api.exception.SaldoInsuficienteException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
public class Cuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;
    
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal saldo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCuenta estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "cuentaOrigen", fetch = FetchType.LAZY)
    private List<Transaccion> transaccionesOrigen = new ArrayList<>();
    
    @OneToMany(mappedBy = "cuentaDestino", fetch = FetchType.LAZY)
    private List<Transaccion> transaccionesDestino = new ArrayList<>();
    
    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        if (saldo == null) {
            saldo = BigDecimal.ZERO;
        }
        if (estado == null) {
            estado = EstadoCuenta.ACTIVA;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
    }
    
    public boolean tieneSaldoSuficiente(BigDecimal monto) {
        return saldo != null && saldo.compareTo(monto) >= 0;
    }
    
    public void debitar(BigDecimal monto) {
        if (!tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException(numeroCuenta, saldo, monto);
        }
        saldo = saldo.subtract(monto);
    }
    
    public void acreditar(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser positivo");
        }
        saldo = saldo.add(monto);
    }
    
    public enum TipoCuenta {
        AHORROS,
        CORRIENTE
    }
    
    public enum EstadoCuenta {
        ACTIVA,
        INACTIVA,
        BLOQUEADA
    }
}

// === ARCHIVO: src/main/java/com/banco/api/model/Cliente.java ===
package com.banco.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String identificacion;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellido;
    
    @Column
    private String email;
    
    @Column
    private String telefono;
    
    @Column
    private String direccion;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_identificacion", nullable = false)
    private TipoIdentificacion tipoIdentificacion;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCliente estado;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Cuenta> cuentas = new ArrayList<>();
    
    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        if (estado == null) {
            estado = EstadoCliente.ACTIVO;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    
    public boolean isActivo() {
        return estado == EstadoCliente.ACTIVO;
    }
    
    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
        cuenta.setCliente(this);
    }
    
    public enum TipoIdentificacion {
        CEDULA,
        PASAPORTE,
        NIT
    }
    
    public enum EstadoCliente {
        ACTIVO,
        INACTIVO,
        SUSPENDIDO
    }
}

=== ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>
    
    <groupId>com.banco</groupId>
    <artifactId>api</artifactId>
    <version>1.0.0</version>
    <name>api</name>
    <description>API Bancaria</description>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.6.0</version>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.3.230</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>3.3.4</version>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/banco/api/service/CuentaService.java ===
package com.banco.api.service;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.dto.TransferenciaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.exception.SaldoInsuficienteException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.model.Transaccion;
import com.banco.api.model.Transaccion.EstadoTransaccion;
import com.banco.api.model.Transaccion.TipoTransaccion;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.TransaccionRepository;
import com.banco.api.util.IdempotenciaUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CuentaService {
    
    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;
    private final IdempotenciaUtil idempotenciaUtil;
    
    public CuentaService(CuentaRepository cuentaRepository,
                         TransaccionRepository transaccionRepository,
                         IdempotenciaUtil idempotenciaUtil) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
        this.idempotenciaUtil = idempotenciaUtil;
    }
    
    public CuentaDTO obtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
            .orElseThrow(() -> new CuentaNoEncontradaException(id));
        return CuentaDTO.fromEntity(cuenta);
    }
    
    public List<CuentaDTO> listarCuentas() {
        return cuentaRepository.findAll().stream()
            .map(CuentaDTO::fromEntity)
            .toList();
    }
    
    public CuentaDTO consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
        return CuentaDTO.fromEntity(cuenta);
    }
    
    public TransferenciaDTO realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, 
                                                   BigDecimal monto, String descripcion) {
        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId)
            .orElseThrow(() -> new CuentaNoEncontradaException(cuentaOrigenId, "Cuenta origen no encontrada"));
        
        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId)
            .orElseThrow(() -> new CuentaNoEncontradaException(cuentaDestinoId, "Cuenta destino no encontrada"));
        
        if (cuentaOrigen.getEstado() != EstadoCuenta.ACTIVA) {
            throw new IllegalArgumentException("La cuenta de origen no está activa");
        }
        
        if (cuentaDestino.getEstado() != EstadoCuenta.ACTIVA) {
            throw new IllegalArgumentException("La cuenta de destino no está activa");
        }
        
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        
        if (!cuentaOrigen.tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException(
                cuentaOrigen.getNumeroCuenta(),
                cuentaOrigen.getSaldo(),
                monto
            );
        }
        
        BigDecimal comision = calcularComision(monto);
        BigDecimal montoTotal = monto.add(comision);
        
        cuentaOrigen.debitar(montoTotal);
        cuentaDestino.acreditar(monto);
        
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);
        
        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaOrigen(cuentaOrigen);
        transaccion.setCuentaDestino(cuentaDestino);
        transaccion.setMonto(monto);
        transaccion.setMontoComision(comision);
        transaccion.setTipoTransaccion(TipoTransaccion.TRANSFERENCIA);
        transaccion.setEstado(EstadoTransaccion.COMPLETADA);
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setFechaProcesamiento(LocalDateTime.now());
        transaccion.setDescripcion(descripcion);
        transaccion.setReferenciaExternna(UUID.randomUUID().toString());
        
        transaccionRepository.save(transaccion);
        
        return new TransferenciaDTO(
            cuentaOrigen.getNumeroCuenta(),
            cuentaDestino.getNumeroCuenta(),
            monto,
            descripcion,
            null
        );
    }
    
    private BigDecimal calcularComision(BigDecimal monto) {
        return monto.multiply(new BigDecimal("0.01"));
    }
    
    public boolean cuentaExiste(String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }
    
    public boolean cuentaActiva(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .map(c -> c.getEstado() == EstadoCuenta.ACTIVA)
            .orElse(false);
    }
}


// === ARCHIVO: src/main/java/com/banco/api/model/Cuenta.java ===
package com.banco.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuentas", indexes = {
    @Index(name = "idx_numero_cuenta", columnList = "numero_cuenta", unique = true),
    @Index(name = "idx_cliente_id", columnList = "cliente_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 20)
    private String numeroCuenta;

    @Column(name = "saldo", nullable = false, precision = 19, scale = 2)
    @Builder.Default
    private BigDecimal saldo = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false, length = 20)
    private TipoCuenta tipoCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoCuenta estado = EstadoCuenta.ACTIVA;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaOrigen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Transaccion> transaccionesOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "cuentaDestino", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Transaccion> transaccionesDestino = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (estado == null) {
            estado = EstadoCuenta.ACTIVA;
        }
        if (saldo == null) {
            saldo = BigDecimal.ZERO;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    public boolean tieneSaldoSuficiente(BigDecimal monto) {
        return this.saldo.compareTo(monto) >= 0;
    }

    public void debitar(BigDecimal monto) {
        if (tieneSaldoSuficiente(monto)) {
            this.saldo = this.saldo.subtract(monto);
        } else {
            throw new com.banco.api.exception.SaldoInsuficienteException(
                    this.numeroCuenta, this.saldo, monto);
        }
    }

    public void acreditar(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    public boolean isActivo() {
        return EstadoCuenta.ACTIVA.equals(this.estado);
    }

    public enum TipoCuenta {
        AHORROS,
        CORRIENTE,
        PLAZO_FIJO,
        VIRTUAL
    }

    public enum EstadoCuenta {
        ACTIVA,
        INACTIVA,
        BLOQUEADA,
        CERRADA
    }
}

// === ARCHIVO: src/main/java/com/banco/api/model/Transaccion.java ===
package com.banco.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones", indexes = {
    @Index(name = "idx_cuenta_origen", columnList = "cuenta_origen_id"),
    @Index(name = "idx_cuenta_destino", columnList = "cuenta_destino_id"),
    @Index(name = "idx_clave_idempotencia", columnList = "clave_idempotencia", unique = true),
    @Index(name = "idx_fecha_transaccion", columnList = "fecha_transaccion")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_origen_id", nullable = false)
    private Cuenta cuentaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_destino_id", nullable = false)
    private Cuenta cuentaDestino;

    @Column(name = "monto", nullable = false, precision = 19, scale = 2)
    private BigDecimal monto;

    @Column(name = "monto_comision", precision = 19, scale = 2)
    @Builder.Default
    private BigDecimal montoComision = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transaccion", nullable = false, length = 20)
    private TipoTransaccion tipoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    @Builder.Default
    private EstadoTransaccion estado = EstadoTransaccion.PENDIENTE;

    @Column(name = "fecha_transaccion", nullable = false)
    private LocalDateTime fechaTransaccion;

    @Column(name = "fecha_procesamiento")
    private LocalDateTime fechaProcesamiento;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "referencia_externa", length = 50)
    private String referenciaExternna;

    @Column(name = "clave_idempotencia", unique = true, length = 50)
    private String claveIdempotencia;

    @Column(name = "codigo_error", length = 10)
    private String codigoError;

    @Column(name = "mensaje_error", length = 500)
    private String mensajeError;

    @PrePersist
    protected void onCreate() {
        if (fechaTransaccion == null) {
            fechaTransaccion = LocalDateTime.now();
        }
        if (estado == null) {
            estado = EstadoTransaccion.PENDIENTE;
        }
        if (montoComision == null) {
            montoComision = BigDecimal.ZERO;
        }
    }

    public void procesar() {
        this.estado = EstadoTransaccion.EXITOSA;
        this.fechaProcesamiento = LocalDateTime.now();
    }

    public void revertir(String motivo) {
        this.estado = EstadoTransaccion.REVERTIDA;
        this.descripcion = "REVERTIDA: " + motivo;
        this.fechaProcesamiento = LocalDateTime.now();
    }

    public void fallar(String codigoError, String mensajeError) {
        this.estado = EstadoTransaccion.FALLIDA;
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.fechaProcesamiento = LocalDateTime.now();
    }

    public enum TipoTransaccion {
        TRANSFERENCIA,
        DEPOSITO,
        RETIRO,
        PAGO,
        COMPRA
    }

    public enum EstadoTransaccion {
        PENDIENTE,
        EXITOSA,
        FALLIDA,
        REVERTIDA,
        CANCELADA
    }
}

// === ARCHIVO: src/main/java/com/banco/api/util/IdempotenciaUtil.java ===
package com.banco.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class IdempotenciaUtil {

    private static final int MAX_ENTRADAS = 10000;
    private static final long DURACION_CACHE_MS = 3600000; // 1 hora

    private final ConcurrentHashMap<String, EntradaIdempotencia> cache = new ConcurrentHashMap<>();

    public IdempotenciaUtil() {
    }

    public boolean esRequisicionIdempotente(String claveIdempotencia) {
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            return false;
        }
        return cache.containsKey(claveIdempotencia);
    }

    public boolean esClaveValida(String claveIdempotencia) {
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            return false;
        }
        if (claveIdempotencia.length() < 10 || claveIdempotencia.length() > 50) {
            return false;
        }
        return true;
    }

    public void registrarRequisicion(String claveIdempotencia, ResultadoOperacion resultado) {
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            return;
        }
        if (cache.size() >= MAX_ENTRADAS) {
            limpiarEntradasExpiradas();
        }
        cache.put(claveIdempotencia, new EntradaIdempotencia(resultado, Instant.now()));
    }

    private void limpiarEntradasExpiradas() {
        Instant limite = Instant.now().minusMillis(DURACION_CACHE_MS);
        cache.entrySet().removeIf(entry -> entry.getValue().instant().isBefore(limite));
    }

    public record ResultadoOperacion(boolean exitosa, Object resultado, String error) {
        public static ResultadoOperacion success(Object resultado) {
            return new ResultadoOperacion(true, resultado, null);
        }

        public static ResultadoOperacion failure(String error) {
            return new ResultadoOperacion(false, null, error);
        }
    }

    private record EntradaIdempotencia(ResultadoOperacion resultado, Instant instant) {
    }
}

// === ARCHIVO: src/main/java/com/banco/api/dto/TransferenciaDTO.java ===
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


// === ARCHIVO: src/main/java/com/banco/api/model/Cliente.java ===
package com.banco.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private Long id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private TipoIdentificacion tipoIdentificacion;
    private EstadoCliente estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private List<Cuenta> cuentas;

    public Cliente() {
        this.cuentas = new ArrayList<>();
    }

    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public boolean isActivo() {
        return estado == EstadoCliente.ACTIVO;
    }

    public void agregarCuenta(Cuenta cuenta) {
        if (this.cuentas == null) {
            this.cuentas = new ArrayList<>();
        }
        this.cuentas.add(cuenta);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public TipoIdentificacion getTipoIdentificacion() { return tipoIdentificacion; }
    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }
    public EstadoCliente getEstado() { return estado; }
    public void setEstado(EstadoCliente estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
    public List<Cuenta> getCuentas() { return cuentas; }
    public void setCuentas(List<Cuenta> cuentas) { this.cuentas = cuentas; }

    public enum TipoIdentificacion {
        CEDULA, RUC, PASAPORTE
    }

    public enum EstadoCliente {
        ACTIVO, INACTIVO, BLOQUEADO
    }
}

// === ARCHIVO: src/main/java/com/banco/api/model/Cuenta.java ===
package com.banco.api.model;

import com.banco.api.model.Cliente;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta")
    private TipoCuenta tipoCuenta;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaOrigen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaccion> transaccionesOrigen = new ArrayList<>();

    @OneToMany(mappedBy = "cuentaDestino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaccion> transaccionesDestino = new ArrayList<>();

    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public boolean tieneSaldoSuficiente(BigDecimal monto) {
        return this.saldo != null && this.saldo.compareTo(monto) >= 0;
    }

    public void debitar(BigDecimal monto) {
        if (tieneSaldoSuficiente(monto)) {
            this.saldo = this.saldo.subtract(monto);
        }
    }

    public void acreditar(BigDecimal monto) {
        if (monto != null && monto.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(monto);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public TipoCuenta getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(TipoCuenta tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    public EstadoCuenta getEstado() { return estado; }
    public void setEstado(EstadoCuenta estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Transaccion> getTransaccionesOrigen() { return transaccionesOrigen; }
    public void setTransaccionesOrigen(List<Transaccion> transaccionesOrigen) { this.transaccionesOrigen = transaccionesOrigen; }
    public List<Transaccion> getTransaccionesDestino() { return transaccionesDestino; }
    public void setTransaccionesDestino(List<Transaccion> transaccionesDestino) { this.transaccionesDestino = transaccionesDestino; }

    public enum TipoCuenta {
        AHORROS, CORRIENTE
    }

    public enum EstadoCuenta {
        ACTIVA, INACTIVA, BLOQUEADA
    }
}

// === ARCHIVO: src/main/java/com/banco/api/dto/CuentaDTO.java ===
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

// === ARCHIVO: src/main/java/com/banco/api/service/CuentaService.java ===
package com.banco.api.service;

import com.banco.api.dto.CuentaDTO;
import com.banco.api.dto.TransferenciaDTO;
import com.banco.api.exception.CuentaNoEncontradaException;
import com.banco.api.exception.SaldoInsuficienteException;
import com.banco.api.model.Cuenta;
import com.banco.api.model.Cuenta.EstadoCuenta;
import com.banco.api.repository.CuentaRepository;
import com.banco.api.repository.TransaccionRepository;
import com.banco.api.util.IdempotenciaUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;
    private final IdempotenciaUtil idempotenciaUtil;

    public CuentaService(CuentaRepository cuentaRepository,
                         TransaccionRepository transaccionRepository,
                         IdempotenciaUtil idempotenciaUtil) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
        this.idempotenciaUtil = idempotenciaUtil;
    }

    public CuentaDTO obtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
            .orElseThrow(() -> new CuentaNoEncontradaException(id));
        return CuentaDTO.fromEntity(cuenta);
    }

    public List<CuentaDTO> listarCuentas() {
        return cuentaRepository.findAll().stream()
            .map(CuentaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Transactional
    public CuentaDTO realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, 
                                           BigDecimal monto, String claveIdempotencia) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        if (claveIdempotencia != null && !idempotenciaUtil.esRequisicionIdempotente(claveIdempotencia)) {
            throw new IllegalArgumentException("Clave de idempotencia ya utilizada");
        }

        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId)
            .orElseThrow(() -> new CuentaNoEncontradaException(cuentaOrigenId));

        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId)
            .orElseThrow(() -> new CuentaNoEncontradaException(cuentaDestinoId));

        if (cuentaOrigen.getEstado() != EstadoCuenta.ACTIVA) {
            throw new IllegalStateException("La cuenta de origen no está activa");
        }

        if (cuentaDestino.getEstado() != EstadoCuenta.ACTIVA) {
            throw new IllegalStateException("La cuenta de destino no está activa");
        }

        if (!cuentaOrigen.tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException(
                cuentaOrigen.getNumeroCuenta(),
                cuentaOrigen.getSaldo(),
                monto
            );
        }

        BigDecimal comision = calcularComision(monto);
        BigDecimal montoTotal = monto.add(comision);

        cuentaOrigen.debitar(montoTotal);
        cuentaDestino.acreditar(monto);

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        if (claveIdempotencia != null) {
            idempotenciaUtil.registrarRequisicion(claveIdempotencia, 
                new IdempotenciaUtil.ResultadoOperacion(true, "Transferencia exitosa"));
        }

        return CuentaDTO.fromEntity(cuentaOrigen);
    }

    public CuentaDTO consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuenta));
        return CuentaDTO.fromEntity(cuenta);
    }

    public TransferenciaDTO realizarTransferencia(String numeroCuentaOrigen, 
                                                   String numeroCuentaDestino,
                                                   BigDecimal monto,
                                                   String descripcion,
                                                   String claveIdempotencia) {
        Cuenta cuentaOrigen = cuentaRepository.findByNumeroCuenta(numeroCuentaOrigen)
            .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuentaOrigen));
        Cuenta cuentaDestino = cuentaRepository.findByNumeroCuenta(numeroCuentaDestino)
            .orElseThrow(() -> new CuentaNoEncontradaException(numeroCuentaDestino));

        if (!cuentaOrigen.tieneSaldoSuficiente(monto)) {
            throw new SaldoInsuficienteException(cuentaOrigen.getNumeroCuenta(), cuentaOrigen.getSaldo(), monto);
        }

        cuentaOrigen.debitar(monto);
        cuentaDestino.acreditar(monto);

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        return new TransferenciaDTO(numeroCuentaOrigen, numeroCuentaDestino, monto, descripcion, claveIdempotencia);
    }

    private BigDecimal calcularComision(BigDecimal monto) {
        return monto.multiply(new BigDecimal("0.01"));
    }

    public boolean cuentaExiste(String numeroCuenta) {
        return cuentaRepository.existsByNumeroCuenta(numeroCuenta);
    }

    public boolean cuentaActiva(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .map(c -> c.getEstado() == EstadoCuenta.ACTIVA)
            .orElse(false);
    }
}

// === ARCHIVO: src/main/java/com/banco/api/util/IdempotenciaUtil.java ===
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
```
