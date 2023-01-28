/*
* Archivo: CiudadRepositorio.java
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

import com.grupoasd.activos.entity.Ciudad;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase que se encarga de gestionar todas las operaciones de persistencia de la
 * tabla Ciudad, para con la base de datos.
 *
 * @author Santiago Rojas Manios
 *
 */
@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad, Serializable> {

    /**
     * Buscar por ID.
     *
     * @param id Id.
     * @return Ciudad.
     */
    Ciudad findById(Integer id);

    /**
     * Buscar por Nobmre.
     *
     * @param nombre Nombre.
     * @return Ciudad.
     */
    Ciudad findByNombre(String nombre);

    /**
     * Buscara todos.
     *
     * @return Ciudad.
     */
    @Override
    List<Ciudad> findAll();
}
