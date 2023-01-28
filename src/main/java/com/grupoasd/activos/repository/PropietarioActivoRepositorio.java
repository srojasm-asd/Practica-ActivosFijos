/*
* Archivo: PropietarioActivoRepositorio.java
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
import com.grupoasd.activos.entity.PropietarioActivo;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de gestionar todas las operaciones de persistencia de la
 * tabla Propietario_Activo, para con la base de datos.
 *
 * @author Santiago Rojas Manios
 *
 */
@Repository
public interface PropietarioActivoRepositorio extends JpaRepository<PropietarioActivo, Serializable> {

    /**
     * Método que consulta en la base de datos una relación de Propietario por
     * Activo por su Id.
     *
     * @param id Id.
     * @return PropietarioActivo
     */
    PropietarioActivo findById(Integer id);

    /**
     * Método que consulta en la base de datos una relación de Propietario por
     * Activo por el Id del Activo.
     *
     * @param activo Activo.
     * @return PropietarioActivo
     */
    PropietarioActivo findByIdActivo(Activo activo);

    /**
     * Método que permite crear una nueva relación de un activo con un
     * propietario en la base de datos.
     *
     * @param propietario Propietario.
     * @return PropietarioActivo
     */
    @Override
    PropietarioActivo save(PropietarioActivo propietario);
}
