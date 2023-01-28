/*
* Archivo: TipoActivoRepositorio.java
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

import com.grupoasd.activos.entity.TipoActivo;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de gestionar todas las operaciones de persistencia de la
 * tabla Tipo_Activo, para con la base de datos.
 *
 * @author Santiago Rojas Manios
 *
 */
@Repository
public interface TipoActivoRepositorio extends JpaRepository<TipoActivo, Serializable> {

    /**
     * Buscar por ID.
     *
     * @param id Id.
     * @return TipoActivo.
     */
    TipoActivo findById(Integer id);
}
