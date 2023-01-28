/*
* Archivo: CiudadController.java
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

import com.grupoasd.activos.entity.Ciudad;
import com.grupoasd.activos.service.ServicioCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que describe los servicios de controlador para con las interfaces de
 * negocio para el objeto Ciudad y permite definir los servicios rest.
 *
 * @author Santiago Rojas Manios
 *
 */
@RestController
public class CiudadController {

    /**
     * ServicioCiudad.
     */
    @Autowired
    private ServicioCiudad servCiudad;

    /**
     * obtener la ciudad.
     *
     * @param id Id.
     * @return Ciudad.
     */
    @RequestMapping(value = "/ciudad/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Ciudad getCiudad(@PathVariable Integer id) {
        return servCiudad.buscarCiudadById(id);
    }

}
