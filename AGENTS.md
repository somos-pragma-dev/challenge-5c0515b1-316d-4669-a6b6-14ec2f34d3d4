# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de una API REST en dominio de banca**.

| | |
|---|---|
| Tema | Java Spring Boot |
| Nivel | junior-l1 |
| Chapter | Generico |
| Especialidad | Inferido del contexto |
| Stack | Java / Spring Boot 3.3.4 |
| Patron arquitectonico | capas estándar (controller-service-repository) |
| Tiempo estimado | 8 horas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `el comando de build o arranque canonico del stack elegido` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `el comando de build o arranque canonico del stack elegido` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Diseño del modelo de dominio**: Modelo de dominio documentado con entidades y relaciones.
- **Fase 2 — Implementación de la API REST**: API REST con endpoints funcionales y manejo de errores.
- **Fase 3 — Documentación de la API con OpenAPI**: Documentación de la API con OpenAPI.
- **Fase 4 — Pruebas y optimización**: API REST con pruebas unitarias y de integración, y optimizada para altas cargas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Boilerplate del stack (1)

Sin esto el proyecto no compila ni arranca. **Es tu trabajo crearlo**, y no toca nada de lo pedagogico: es andamiaje del stack.

- [ ] **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### 2. Referencias colgando (23)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/banco/api/service/CuentaService.java` — `ResultadoOperacion`
      ResultadoOperacion se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.banco.api.util.ResultadoOperacion.
- [ ] `src/test/java/com/banco/api/controller/CuentaControllerTest.java` — `CuentaNoEncontradaException`
      CuentaNoEncontradaException se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.banco.api.exception.CuentaNoEncontradaException.
- [ ] `src/test/java/com/banco/api/controller/CuentaControllerTest.java` — `SaldoInsuficienteException`
      SaldoInsuficienteException se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.banco.api.exception.SaldoInsuficienteException.
- [ ] `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Cuenta`
      El import com.banco.api.model.Cuenta no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `src/main/java/com/banco/api/dto/ErrorResponseDTO.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.media.Schema pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/api/config/OpenApiConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/api/controller/CuentaController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getCuentaOrigen`
      Se invoca `getCuentaOrigen` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getCuentaDestino`
      Se invoca `getCuentaDestino` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getMonto`
      Se invoca `getMonto` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getDescripcion`
      Se invoca `getDescripcion` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/api/dto/TransferenciaDTO.java` — `Transaccion.getClaveIdempotencia`
      Se invoca `getClaveIdempotencia` sobre `Transaccion`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/api/service/CuentaService.java` — `CuentaRepository.findById`
      Se invoca `findById` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/api/service/CuentaService.java` — `CuentaRepository.findAll`
      Se invoca `findAll` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banco/api/service/CuentaService.java` — `CuentaRepository.save`
      Se invoca `save` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaRepository.findById`
      Se invoca `findById` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.id`
      Se invoca `id` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.numeroCuenta`
      Se invoca `numeroCuenta` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.saldo`
      Se invoca `saldo` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.tipoCuenta`
      Se invoca `tipoCuenta` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaRepository.findAll`
      Se invoca `findAll` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaDTO.size`
      Se invoca `size` sobre `CuentaDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banco/api/service/CuentaServiceTest.java` — `CuentaRepository.save`
      Se invoca `save` sobre `CuentaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (20)

- `pom.xml`
- `src/main/java/com/banco/api/ApiApplication.java`
- `src/main/resources/application.properties`
- `src/main/java/com/banco/api/model/Cuenta.java`
- `src/main/java/com/banco/api/model/Cliente.java`
- `src/main/java/com/banco/api/model/Transaccion.java`
- `src/main/java/com/banco/api/dto/CuentaDTO.java`
- `src/main/java/com/banco/api/dto/TransferenciaDTO.java`
- `src/main/java/com/banco/api/dto/ErrorResponseDTO.java`
- `src/main/java/com/banco/api/repository/CuentaRepository.java`
- `src/main/java/com/banco/api/repository/TransaccionRepository.java`
- `src/main/java/com/banco/api/config/OpenApiConfig.java`
- `src/main/java/com/banco/api/exception/SaldoInsuficienteException.java`
- `src/main/java/com/banco/api/service/CuentaService.java`
- `src/main/java/com/banco/api/controller/CuentaController.java`
- `src/main/java/com/banco/api/exception/CuentaNoEncontradaException.java`
- `src/main/java/com/banco/api/exception/GlobalExceptionHandler.java`
- `src/main/java/com/banco/api/util/IdempotenciaUtil.java`
- `src/test/java/com/banco/api/controller/CuentaControllerTest.java`
- `src/test/java/com/banco/api/service/CuentaServiceTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/banco/api`
- `src/main/java/com/banco/api/controller`
- `src/main/java/com/banco/api/service`
- `src/main/java/com/banco/api/repository`
- `src/main/java/com/banco/api/model`
- `src/main/java/com/banco/api/dto`
- `src/main/java/com/banco/api/exception`
- `src/main/java/com/banco/api/config`
- `src/main/java/com/banco/api/util`
- `src/test/java/com/banco/api/controller`
- `src/test/java/com/banco/api/service`
- `src/test/java/com/banco/api/repository`

## Verificacion

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar (controller-service-repository)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Crear una API REST con Spring Boot, JPA y documentación OpenAPI

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
