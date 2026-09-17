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