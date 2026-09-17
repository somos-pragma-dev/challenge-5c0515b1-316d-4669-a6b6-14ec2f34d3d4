# Desarrollo de una API REST en dominio de banca

En un banco, se requiere desarrollar una API REST que permita a los clientes consultar su saldo y realizar transferencias. La API debe ser idempotente y manejar adecuadamente los errores del dominio, como saldos insuficientes o cuentas inexistentes. Deberás asegurar que la API sea robusta y pueda manejar altas cargas de solicitudes.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Java Spring Boot |
| **Nivel** | junior-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Diseño del modelo de dominio

**Objetivo:** Definir las entidades y relaciones necesarias para el dominio de banca.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las entidades clave del dominio (cuenta, cliente, transacción).
- Definir las relaciones entre estas entidades.
- Establecer las restricciones y validaciones necesarias (saldo mínimo, cuentas activas).

**Entregable:** Modelo de dominio documentado con entidades y relaciones.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los atributos necesarios para cada entidad.
- Piensa en las relaciones uno-a-muchos y muchos-a-muchos.

</details>

### Fase 2: Implementación de la API REST

**Objetivo:** Crear las endpoints para consultar saldo y realizar transferencias.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar los endpoints `/consultar-saldo` y `/realizar-transferencia`.
- Asegurar que los endpoints sean idempotentes.
- Manejar adecuadamente los errores del dominio.

**Entregable:** API REST con endpoints funcionales y manejo de errores.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza claves de idempotencia para asegurar la idempotencia de las operaciones.
- Considera los diferentes modos de falla y cómo manejarlos.

</details>

### Fase 3: Documentación de la API con OpenAPI

**Objetivo:** Documentar la API utilizando OpenAPI.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Crear la documentación de la API utilizando OpenAPI.
- Asegurar que la documentación sea clara y completa.
- Incluir ejemplos de solicitudes y respuestas.

**Entregable:** Documentación de la API con OpenAPI.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas como Swagger para generar la documentación.
- Asegúrate de que la documentación incluya todos los endpoints y sus posibles respuestas.

</details>

### Fase 4: Pruebas y optimización

**Objetivo:** Realizar pruebas unitarias y de integración, y optimizar la API para manejar altas cargas.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Escribir pruebas unitarias y de integración para los endpoints.
- Optimizar la API para manejar altas cargas de solicitudes.
- Asegurar que la API sea robusta y escalable.

**Entregable:** API REST con pruebas unitarias y de integración, y optimizada para altas cargas.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de testing como JUnit y Mockito.
- Considera técnicas de optimización como caché y limitación de tasa.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Cuáles son las entidades y relaciones clave en el dominio de banca para la API REST?
- **paraQueSirve**: ¿Para qué sirven los endpoints `/consultar-saldo` y `/realizar-transferencia` en el contexto del dominio de banca?
- **comoSeUsa**: ¿Cómo se utiliza la idempotencia en los endpoints de la API REST para asegurar la consistencia de las operaciones?
- **erroresComunes**: ¿Cuáles son los errores comunes que deben manejarse en la API REST y cómo se pueden abordar?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la optimización de la API REST para manejar altas cargas de solicitudes?

## Criterios de Evaluacion

- Definición clara de las entidades y relaciones en el dominio de banca.
- Implementación funcional de los endpoints `/consultar-saldo` y `/realizar-transferencia` con manejo adecuado de errores.
- Documentación completa y clara de la API utilizando OpenAPI.
- Pruebas unitarias y de integración que demuestren el funcionamiento correcto de la API.
- Optimización de la API para manejar altas cargas de solicitudes.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
el comando de build o arranque canonico del stack elegido
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
