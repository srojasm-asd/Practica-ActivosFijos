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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(value = "Propietario Activos", tags = {"Propietario Activos"},
        description = "Api para consultar información de los propietarios de los activos de la empresa.")
@RequestMapping("${app.context-api}/propietarios-activos")
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
    @ApiOperation(value = "Servicio que consulta el propietario de un activo por el Id.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa del componente",
                        response = PropietarioActivo.class),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @GetMapping(value = "/id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PropietarioActivo> getPropietarioActivo(@PathVariable Integer id) {
        log.info("id_propietario:" + id);
        Optional<PropietarioActivo> respuesta = servPropietarioActivo.buscarPropietarioActivoById(id);
        if (respuesta.isPresent()) {
            return new ResponseEntity<>(respuesta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Servicio rest que permite crear un nuevo Propietario Activo en la base de
     * datos.
     *
     * @param idActivo Id Activo.
     * @param idPersona Id Persona.
     * @param idArea Id Area.
     * @return PropietarioActivo
     */
    @ApiOperation(value = "Servicio que crear un nuevo Propietario de un  Activo.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa del componente",
                        response = PropietarioActivo.class),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @PutMapping(value = "/{idActivo}/{idPersona}/{idArea}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PropietarioActivo> createPropietarioActivo(@PathVariable Integer idActivo,
            @PathVariable Integer idPersona, @PathVariable Integer idArea) {

        Optional<PropietarioActivo> respuesta = servPropietarioActivo.crear(idActivo, idPersona, idArea);
        if (respuesta.isPresent()) {
            return new ResponseEntity<>(respuesta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
