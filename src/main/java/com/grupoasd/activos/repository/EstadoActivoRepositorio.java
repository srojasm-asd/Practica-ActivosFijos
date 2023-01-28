/*
* Archivo: EstadoActivoRepositorio.java
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

import com.grupoasd.activos.entity.EstadoActivo;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Estado Activo.
 *
 * @author asd
 */
@Repository
public interface EstadoActivoRepositorio extends JpaRepository<EstadoActivo, Serializable> {

    /**
     * Buscar por Id.
     *
     * @param id Id.
     * @return EstadoActivo.
     */
    EstadoActivo findById(Integer id);
}
