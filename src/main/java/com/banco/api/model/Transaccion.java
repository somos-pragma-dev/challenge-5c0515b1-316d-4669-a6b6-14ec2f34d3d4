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