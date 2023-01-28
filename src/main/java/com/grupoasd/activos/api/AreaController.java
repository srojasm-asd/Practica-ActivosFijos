/*
* Archivo: AreaController.java
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

import com.grupoasd.activos.entity.Area;
import com.grupoasd.activos.service.ServicioArea;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que describe los servicios de controlador para con las interfaces de
 * negocio para el objeto Area y permite definir los servicios rest.
 *
 * @author Santiago Rojas Manios
 *
 */
@Api(value = "Áreas", tags = {"Areas"},
        description = "Api para consultar información de las áreas administrativas de la empresa.")
@RequestMapping("${app.context-api}/areas")
@RestController
public class AreaController {

    /**
     * ServicioArea.
     */
    @Autowired
    private ServicioArea servArea;

    /**
     * Servicio rest que consulta en la base de datos todos los registros de las
     * Areas, retornando una lista.
     *
     * @return List-Area
     */
    @ApiOperation(value = "Servicio que lista todas las áreas administrativas de la empresa.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa del componente",
                        response = Area.class),
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
    public ResponseEntity<List<Area>> consultarAreas() {
        List<Area> resultado = servArea.buscarTodosLasAreas();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    /**
     * Servicio rest que consulta en la base de datos un Area por su Id y
     * retorna un Json con la información del Actio.
     *
     * @param id Id.
     * @return Area
     */
    @ApiOperation(value = "Servicio que consulta una áreas administrativas de la empresa, por su ID.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Area.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Área no encontrada"),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Area> consultarArea(
            @PathVariable @ApiParam(name = "id", value = "Id del Área.", example = "1") Integer id) {

        Optional<Area> area = servArea.buscarAreaById(id);
        if (area.isPresent()) {
            return new ResponseEntity<>(area.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
