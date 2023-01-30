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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(value = "Ciudades", tags = {"Ciudades"},
        description = "Api para consultar información de las ciudades.")
@RequestMapping("${app.context-api}/ciudades")
@RestController
public class CiudadController {

    /**
     * ServicioCiudad.
     */
    @Autowired
    private ServicioCiudad servCiudad;

    /**
     * Servicio rest que consulta en la base de datos todos los registros de las
     * Ciudades, retornando una lista.
     *
     * @return List-Ciudad
     */
    @ApiOperation(value = "Servicio que lista todas las Ciudades.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa del componente",
                        response = Ciudad.class),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ciudad>> consultarCiudades() {
        List<Ciudad> resultado = servCiudad.buscarTodasLasCiudades();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    /**
     * Obtener la ciudad.
     *
     * @param id Id.
     * @return Ciudad.
     */
    @ApiOperation(value = "Servicio que consulta una ciudad, por su ID.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Ciudad.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Ciudad no encontrada"),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ciudad> consultarCiudad(@PathVariable Integer id) {
        Optional<Ciudad> resultado = servCiudad.buscarCiudadById(id);
        if (resultado.isPresent()) {
            return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
