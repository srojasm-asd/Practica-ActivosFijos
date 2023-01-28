/*
* Archivo: PropietarioActivoController.java
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
package com.grupoasd.activos.api;

import com.grupoasd.activos.entity.PropietarioActivo;
import com.grupoasd.activos.service.ServicioPropietarioActivo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que describe los servicios de controlador para con las interfaces de
 * negocio para el objeto PropietarioActivo y permite definir los servicios
 * rest.
 *
 * @author Santiago Rojas Manios
 *
 */
@Slf4j
@RestController
public class PropietarioActivoController {

    /**
     * ServicioPropietarioActivo.
     */
    @Autowired
    private ServicioPropietarioActivo servPropietarioActivo;

    /**
     * Servicio rest que consulta en la base de datos un Propietario de un
     * Activo por su Id y retorna un Json con la información del
     * PropietarioActivo.
     *
     * @param id Id.
     * @return PropietarioActivo
     */
    @GetMapping(value = "/propietario/id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PropietarioActivo getPropietarioActivo(@PathVariable Integer id) {
        log.info("id_propietario:" + id);
        return servPropietarioActivo.buscarPropietarioActivoById(id);
    }

    /**
     * Servicio rest que permite crear un nuevo Activo en la base de datos.
     *
     * @param idActivo Id Activo.
     * @param idPersona Id Persona.
     * @param idArea Id Area.
     * @return PropietarioActivo
     */
    @PutMapping(value = "/activo/propietario/asignar/{idActivo}/{idPersona}/{idArea}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public PropietarioActivo createPropietarioActivo(@PathVariable Integer idActivo,
            @PathVariable Integer idPersona, @PathVariable Integer idArea) {
        return servPropietarioActivo.crear(idActivo, idPersona, idArea);
    }
}
