/*
* Archivo: ActivoRepositorio.java
* Fecha: 23/01/2023
* Todos los derechos de propiedad intelectual e industrial sobre esta
* aplicacion son de propiedad exclusiva del SANTIAGO ROJAS MANIOS
* Su uso, alteracion, reproduccion o modificacion sin el debido
* consentimiento por escrito de GRUPO ASD S.A.S. quedan totalmente prohibidos.
* 
* Este programa se encuentra protegido por las disposiciones de la
* Ley 23 de 1982 y demas normas concordantes sobre derechos de autor y
* propiedad intelectual. Su uso no autorizado dara lugar a las sanciones
* previstas en la Ley.
 */
package com.grupoasd.activos.repository;

import com.grupoasd.activos.entity.Activo;
import com.grupoasd.activos.entity.TipoActivo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de gestionar todas las operaciones de persistencia de la
 * tabla Activo, para con la base de datos.
 *
 * @author Santiago Rojas Manios
 *
 */
@Repository
public interface ActivoRepositorio extends JpaRepository<Activo, Serializable> {

    /**
     * Método que consulta en la base de datos todos los Activos.
     *
     * @return List-Activo
     */
    @Override
    List<Activo> findAll();

    /**
     * Método que consulta en la base de datos un Activo por su Id.
     *
     * @param id Id.
     * @return Activo
     */
    Optional<Activo> findById(Integer id);

    /**
     * Método que consulta en la base de datos un Activo por su Tipo.
     *
     * @param tipo Tipo.
     * @return Activo
     */
    @Deprecated
    List<Activo> findByIdTipoActivo(TipoActivo tipo);

    /**
     * Método que consulta en la base de datos un Activo por su Tipo.
     *
     * @param tipo Tipo.
     * @return Activo
     */
    @Query(value = "SELECT a.* FROM Activo a WHERE a.id_tipo_activo = :tipo", nativeQuery = true)
    List<Activo> buscarByTipo(@Param("tipo") Integer tipo);

    /**
     * Método que consulta en la base de datos un Activo por su Serial.
     *
     * @param serial Serial.
     * @return Activo
     */
    @Query(value = "SELECT a.* FROM Activo a WHERE upper(a.serial) = upper(:serial)", nativeQuery = true)
    List<Activo> buscarBySerial(@Param("serial") String serial);

    /**
     * Método que consulta en la base de datos un Activo por su Fecha de Compra.
     *
     * @param fechaCompra Fecha Compra.
     * @return Activo
     */
    @Query(value = "SELECT a.* FROM Activo a WHERE a.fecha_compra = :fechaCompra", nativeQuery = true)
    List<Activo> buscarByFechaCompra(@Param("fechaCompra") Date fechaCompra);

    /**
     * Método que permite crear un nueva entidad de tipo Activo en la base de.
     * datos
     *
     * @param activo Activo.
     * @return Activo
     */
    @Override
    Activo save(Activo activo);

}
