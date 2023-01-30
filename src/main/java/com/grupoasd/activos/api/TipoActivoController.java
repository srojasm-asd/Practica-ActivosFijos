/*
* Archivo: TipoActivoController.java
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

import com.grupoasd.activos.entity.TipoActivo;
import com.grupoasd.activos.service.ServicioTipoActivo;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que describe los servicios de controlador para con las interfaces de
 * negocio para el objeto TipoActivo y permite definir los servicios rest.
 *
 * @author Santiago Rojas Manios
 *
 */
@Api(value = "Tipo Activo", tags = {"Tipo Activo"},
        description = "Api para consultar información del Tipo de Activos.")
@RequestMapping("${app.context-api}/tipo-activo")
@RestController
public class TipoActivoController {

    /**
     * ServicioTipoActivo.
     */
    @Autowired
    private ServicioTipoActivo servTipoActivo;

    /**
     * Obtener activos.
     *
     * @param id Id.
     * @return TipoActivo.
     */
    @ApiOperation(value = "Servicio que consulta un tipo de activo, por su ID.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = TipoActivo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Tipo Activo no encontrado"),
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
    public ResponseEntity<TipoActivo> getTipoActivo(@PathVariable Integer id) {
        Optional<TipoActivo> resultado = servTipoActivo.buscarTipoActivoById(id);
        if (resultado.isPresent()) {
            return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Obtener todos los tipos de activos.
     *
     * @return List-TipoActivo.
     */
    @ApiOperation(value = "Servicio que consulta todos los Tipos de Activo.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = TipoActivo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Tipo Activo no encontrado"),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TipoActivo>> consultarTiposActivos() {
        List<TipoActivo> resultado = servTipoActivo.buscarTipoActivos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

}
