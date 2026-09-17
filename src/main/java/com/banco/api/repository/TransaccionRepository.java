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