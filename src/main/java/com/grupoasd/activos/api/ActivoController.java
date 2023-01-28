/*
* Archivo: ActivoController.java
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

import com.grupoasd.activos.entity.Activo;
import com.grupoasd.activos.service.ServicioActivo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que describe los servicios de controlador para con las interfaces de
 * negocio para el objeto Activo y permite definir los servicios rest.
 *
 * @author Santiago Rojas Manios
 *
 */
@Api(value = "Activos", tags = {"Activos"},
        description = "Api para consultar información de los activos de la empresa.")
@RequestMapping("${app.context-api}/activos")
@RestController
public class ActivoController {

    /**
     * ServicioActivo.
     */
    @Autowired
    private ServicioActivo servActivo;

    /**
     * Servicio rest que consulta en la base de datos todos los registros de los
     * Activos, retornando una lista.
     *
     * @return List-Activo
     */
    @ApiOperation(value = "Servicio que lista todos los activos de la empresa.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa del componente",
                        response = Activo.class),
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
    public ResponseEntity<List<Activo>> consultarActivos() {
        List<Activo> resultado = servActivo.buscarTodosLosActivos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    /**
     * Servicio rest que consulta en la base de datos un Activo por su Id y
     * retorna un Json con la información del Actio.
     *
     * @param id Id.
     * @return Activo
     */
    @ApiOperation(value = "Servicio que consulta una registro de activo de la empresa, por su ID.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Activo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Activo no encontrado"),
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
    public ResponseEntity<Activo> consultarActivo(
            @PathVariable @ApiParam(name = "id", value = "Id del Activo.", example = "1") Integer id) {
        Optional<Activo> respuesta = servActivo.buscarActivoById(id);
        if (respuesta.isPresent()) {
            return new ResponseEntity<>(respuesta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Servicio rest que consulta en la base de datos un Activo por su Id_Tipo
     * (llave de la tabla tipo_activo) y retorna una lista de Activos.
     *
     * @param id Id.
     * @return List-Activo
     */
    @ApiOperation(value = "Servicio que consulta una registro de activo de la empresa, por su Tipo.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Activo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Activo no encontrado"),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @GetMapping(value = "/tipo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Activo> getActivosByTipo(
            @PathVariable @ApiParam(name = "id", value = "Id del tipo.", example = "1") Integer id) {
        return servActivo.buscarActivosByTipo(id);
    }

    /**
     * Servicio rest que consulta en la base de datos un Activo por su serial y
     * retorna una lista de Activos.
     *
     * @param serial Serial.
     * @return List-Activo
     */
    @ApiOperation(value = "Servicio que consulta una registro de activo de la empresa, por su Serial.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Activo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Activo no encontrado"),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @GetMapping(value = "/serial/{serial}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Activo> getActivosBySerial(
            @PathVariable @ApiParam(name = "serial", value = "Serial.", example = "1") String serial) {
        return servActivo.buscarActivosBySerial(serial);
    }

    /**
     * Servicio rest que consulta en la base de datos un Activo por su fecha de
     * compra y retorna una lista de Activos.
     *
     * @param compra FechaCompra
     * @return List-Activo
     */
    @ApiOperation(value = "Servicio que consulta una registro de activo de la empresa, por su fecha de compra.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Activo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Activo no encontrado"),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @GetMapping(value = "/fecha",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Activo> getActivosByFechaCompra(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
            @ApiParam(name = "compra", value = "Fecha de Compra.", example = "2023-01-26") Date compra) {
        return servActivo.buscarActivosByFechaCompra(compra);
    }

    /**
     * Servicio rest que permite crear un nuevo Activo en la base de datos.
     *
     * @param activo Activo.
     * @return Activo
     */
    @PutMapping(value = "/crear", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Activo createActivo(@RequestBody @Valid Activo activo) {
        return servActivo.crear(activo);
    }

    /**
     * Servicio rest que permite editar un Activo en la base de datos.
     *
     * @param activo Activo.
     * @return Activo
     */
    @PostMapping(value = "/editar", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Activo editActivo(@RequestBody @Valid Activo activo) {
        return servActivo.editar(activo);
    }

    /**
     * Servicio rest que permite editar el atributo serial un Activo en la base
     * de datos.
     *
     * @param id Id.
     * @param serial Serial.
     * @return Activo
     */
    @PostMapping(value = "/editar/serial/{id}/{serial}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Activo editSerialActivo(@PathVariable Integer id, @PathVariable String serial) {
        return servActivo.editarSerialAcivoById(id, serial).get();
    }

    /**
     * Servicio rest que permite editar el atributo serial un Activo en la base
     * de datos.
     *
     * @param id Id.
     * @param fechaBaja FechaBaja.
     * @return Activo
     */
    @PostMapping(value = "/activo/editar/fecha_baja/{id}/{fecha_baja}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Activo editFechaBajaActivo(@PathVariable Integer id,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaBaja) {
        return servActivo.editarFechaBajaAcivoById(id, fechaBaja).get();
    }

    /**
     * Servicio rest que permite eliminar un Activo en la base de datos.
     *
     * @param id Id.
     * @return true-false
     */
    @DeleteMapping(value = "/eliminar/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean deleteActivo(@PathVariable Integer id) {
        return servActivo.eliminar(id);
    }
}
