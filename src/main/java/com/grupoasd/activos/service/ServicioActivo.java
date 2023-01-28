/*
* Archivo: ServicioActivo.java
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
package com.grupoasd.activos.service;

import com.grupoasd.activos.entity.Activo;
import com.grupoasd.activos.repository.ActivoRepositorio;
import com.grupoasd.activos.repository.EstadoActivoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que inyecta de la capa de repositorio de la tabla Activo, para permitir
 * implementar los metodos a nivel de capa de servicio.
 *
 * @author Santiago Rojas Manios
 *
 */
@Slf4j
@Service
public class ServicioActivo {

    /**
     * Id Estado de Baja.
     */
    private static final int ID_ESTADO_DEBAJA = 3;

    /**
     * ActivoRepositorio.
     */
    @Autowired
    private ActivoRepositorio repoActivo;
    /**
     * EstadoActivoRepositorio.
     */
    @Autowired
    private EstadoActivoRepositorio repoEstadoActivo;

    /**
     * Método que consulta en la base de datos todos los Activos.
     *
     * @return List-Activo
     */
    public List<Activo> buscarTodosLosActivos() {
        List<Activo> respuesta = null;
        try {
            respuesta = repoActivo.findAll();
            // logger.info(respuesta.size()+"");
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que consulta en la base de datos un Activo por su Id.
     *
     * @param id Id.
     * @return Activo
     */
    public Optional<Activo> buscarActivoById(Integer id) {
        Optional<Activo> respuesta = Optional.empty();
        try {
            respuesta = repoActivo.findById(id);
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que consulta en la base de datos un Activo por su Tipo.
     *
     * @param tipo Tipo.
     * @return Activo
     */
    public List<Activo> buscarActivosByTipo(Integer tipo) {
        List<Activo> respuesta = null;
        try {
            respuesta = repoActivo.buscarByTipo(tipo);
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que consulta en la base de datos un Activo por su Serial.
     *
     * @param serial Serial.
     * @return Activo
     */
    public Optional<Activo> buscarActivosBySerial(String serial) {
        Optional<Activo> respuesta = Optional.empty();
        try {
            respuesta = Optional.of(repoActivo.buscarBySerial(serial).get(0));
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que consulta en la base de datos un Activo por su Fecha de Compra.
     *
     * @param fechaCompra Fecha Compra.
     * @return Activo.
     */
    public List<Activo> buscarActivosByFechaCompra(Date fechaCompra) {
        List<Activo> respuesta = null;
        try {
            respuesta = repoActivo.buscarByFechaCompra(fechaCompra);
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que permite crear un nueva entidad de tipo Activo en la base de
     * datos.
     *
     * @param activo Activo.
     * @return Activo
     */
    public Optional<Activo> crear(Activo activo) {
        Optional<Activo> respuesta = Optional.empty();
        try {
            respuesta = Optional.of(repoActivo.save(activo));
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que permite editar una entidad de tipo Activo en la base de datos.
     *
     * @param activo Activo.
     * @return Activo
     */
    public Optional<Activo> editar(Activo activo) {
        Optional<Activo> respuesta = Optional.empty();
        try {
            respuesta = Optional.of(repoActivo.save(activo));
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que permite editar el atributo serial a una entidad de tipo Activo
     * en la base de datos.
     *
     * @param id Id.
     * @param serial Serial.
     * @return Activo
     */
    public Optional<Activo> editarSerialAcivoById(Integer id, String serial) {
        Optional<Activo> respuesta = Optional.empty();
        try {
            respuesta = repoActivo.findById(id);
            if (respuesta.isPresent()) {
                respuesta.get().setSerial(serial);
                respuesta = Optional.of(repoActivo.save(respuesta.get()));
            }
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que permite editar el atributo fecha_baja a una entidad de tipo
     * Activo en la base de datos.
     *
     * @param id Id.
     * @param fechaBaja Fecha Baja.
     * @return Activo
     */
    public Optional<Activo> editarFechaBajaAcivoById(Integer id, Date fechaBaja) {
        Optional<Activo> respuesta = Optional.empty();
        try {
            respuesta = repoActivo.findById(id);
            if (respuesta.isPresent() && respuesta.get().getFechaCompra().before(fechaBaja)) {
                respuesta.get().setFechaBaja(fechaBaja);
                respuesta.get().setIdEstadoActivo(repoEstadoActivo.findById(ID_ESTADO_DEBAJA));
                respuesta = Optional.of(repoActivo.save(respuesta.get()));
            }
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que permite eliminar una entidad de tipo Activo en la base de
     * datos.
     *
     * @param id Id.
     * @return true-false
     */
    public boolean eliminar(Integer id) {
        boolean respuesta = false;
        try {
            Optional<Activo> activoEliminar = repoActivo.findById(id);
            if (activoEliminar.isPresent()) {
                repoActivo.delete(activoEliminar.get());
                respuesta = true;
            }
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }
}
