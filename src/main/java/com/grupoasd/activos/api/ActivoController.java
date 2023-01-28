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
    @ApiOperation(value = "Servicio que consulta un registro de activo de la empresa, por su ID.")
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
    @ApiOperation(value = "Servicio que consulta un registro de activo de la empresa, por su Tipo.")
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
    public ResponseEntity<List<Activo>> getActivosByTipo(
            @PathVariable @ApiParam(name = "id", value = "Id del tipo.", example = "1") Integer id) {
        List<Activo> resultado = servActivo.buscarActivosByTipo(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    /**
     * Servicio rest que consulta en la base de datos un Activo por su serial y
     * retorna una lista de Activos.
     *
     * @param serial Serial.
     * @return List-Activo
     */
    @ApiOperation(value = "Servicio que consulta un registro de activo de la empresa, por su Serial.")
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
    public ResponseEntity<Activo> getActivosBySerial(
            @PathVariable @ApiParam(name = "serial", value = "Serial.", example = "1") String serial) {
        Optional<Activo> resultado = servActivo.buscarActivosBySerial(serial);
        return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
    }

    /**
     * Servicio rest que consulta en la base de datos un Activo por su fecha de
     * compra y retorna una lista de Activos.
     *
     * @param compra FechaCompra
     * @return List-Activo
     */
    @ApiOperation(value = "Servicio que consulta un registro de activo de la empresa, por su fecha de compra.")
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
    public ResponseEntity<List<Activo>> getActivosByFechaCompra(
            @ApiParam(name = "compra", value = "Fecha de Compra.", example = "2023-01-26")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date compra) {
        List<Activo> resultado = servActivo.buscarActivosByFechaCompra(compra);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    /**
     * Servicio rest que permite crear un nuevo Activo en la base de datos.
     *
     * @param activo Activo.
     * @return Activo
     */
    @ApiOperation(value = "Servicio que crea un registro de activo de la empresa.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 201,
                        message = "Activo creado correctamente.",
                        response = Activo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Activo ya existe."),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @PutMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Activo> createActivo(@RequestBody @Valid Activo activo) {
        Optional<Activo> activoEnBase = servActivo.buscarActivosBySerial(activo.getSerial());
        if (activoEnBase.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            Optional<Activo> respuesta = servActivo.crear(activo);
            if (respuesta.isPresent()) {
                return new ResponseEntity<>(respuesta.get(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * Servicio rest que permite editar un Activo en la base de datos.
     *
     * @param activo Activo.
     * @return Activo
     */
    @ApiOperation(value = "Servicio que actualizar un registro de activo de la empresa.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Activo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Activo con serial ya existe."),
                @ApiResponse(
                        code = 404,
                        message = "Sin información - Activo no encontrado."),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Activo> editActivo(@RequestBody @Valid Activo activo) {
        Optional<Activo> activoId = servActivo.buscarActivoById(activo.getId());
        if (activoId.isPresent()) {
            Optional<Activo> activoSerial = servActivo.buscarActivosBySerial(activo.getSerial());
            if (activoSerial.isPresent() && activoId.get().getId() == activoSerial.get().getId()
                    && activoId.get().getSerial().toLowerCase().equals(activoSerial.get().getSerial().toLowerCase())) {
                //Se edita el activo por que el ID existe y el Serial es del ID actualizar.
                Optional<Activo> respuesta = servActivo.editar(activo);
                return new ResponseEntity<>(respuesta.get(), HttpStatus.OK);
            } else if (!activoSerial.isPresent()) {
                //Se edita el activo por que el ID exite y el Serial actualizar no existe.
                Optional<Activo> respuesta = servActivo.editar(activo);
                return new ResponseEntity<>(respuesta.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Servicio rest que permite editar el atributo serial un Activo en la base
     * de datos.
     *
     * @param id Id.
     * @param serial Serial.
     * @return Activo
     */
    @ApiOperation(value = "Servicio que actualiza el serial de un registro de activo de la empresa.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Activo.class),
                @ApiResponse(
                        code = 204,
                        message = "Sin información - Activo con serial ya existe."),
                @ApiResponse(
                        code = 404,
                        message = "Sin información - Activo no encontrado."),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @PostMapping(value = "/{id}/serial/{serial}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Activo> editSerialActivo(
            @PathVariable @ApiParam(name = "id", value = "Id del Activo.", example = "1") Integer id,
            @PathVariable @ApiParam(name = "serial", value = "Serial del activo.", example = "E0001") String serial) {

        Optional<Activo> activoId = servActivo.buscarActivoById(id);
        if (activoId.isPresent()) {
            Optional<Activo> activoSerial = servActivo.buscarActivosBySerial(serial);
            if (!activoSerial.isPresent()) {
                //Se edita el activo por que el ID exite y el Serial actualizar no existe.
                Optional<Activo> resultado = Optional.of(servActivo.editarSerialAcivoById(id, serial).get());
                return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Servicio rest que permite editar el atributo serial un Activo en la base
     * de datos.
     *
     * @param id Id.
     * @param fechaBaja FechaBaja.
     * @return Activo
     */
    @ApiOperation(value = "Servicio que actualiza la fecha de baja de un registro de activo de la empresa.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        code = 200,
                        message = "Respuesta exitosa",
                        response = Activo.class),
                @ApiResponse(
                        code = 404,
                        message = "Sin información - Activo no encontrado."),
                @ApiResponse(
                        code = 400,
                        message = "Bad Request",
                        reference = "La solicitud realizada no cumple con las validaciones de datos implementada"),
                @ApiResponse(
                        code = 500,
                        message = "Error interno del servidor")
            }
    )
    @PostMapping(value = "/{id}/fechabaja/{fechabaja}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Activo> editFechaBajaActivo(
            @PathVariable @ApiParam(name = "id", value = "Id del Activo.", example = "1") Integer id,
            @PathVariable @ApiParam(name = "fechabaja", value = "Fecha de baja.")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaBaja) {

        Optional<Activo> activoId = servActivo.buscarActivoById(id);
        if (activoId.isPresent()) {
            Optional<Activo> resultado = Optional.of(servActivo.editarFechaBajaAcivoById(id, fechaBaja).get());
            return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
