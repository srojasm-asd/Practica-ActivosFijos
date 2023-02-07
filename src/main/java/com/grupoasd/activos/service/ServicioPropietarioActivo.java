/*
* Archivo: ServicioPropietarioActivo.java
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
import com.grupoasd.activos.entity.Area;
import com.grupoasd.activos.entity.Persona;
import com.grupoasd.activos.entity.PropietarioActivo;
import com.grupoasd.activos.model.NuevoPropietario;
import com.grupoasd.activos.repository.ActivoRepositorio;
import com.grupoasd.activos.repository.AreaRepositorio;
import com.grupoasd.activos.repository.PersonaRepositorio;
import com.grupoasd.activos.repository.PropietarioActivoRepositorio;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que inyecta de la capa de repositorio de la tabla Propietario_Activo,
 * para permitir implementar los metodos a nivel de capa de servicio.
 *
 * @author Santiago Rojas Manios
 *
 */
@Slf4j
@Service
public class ServicioPropietarioActivo {

    /**
     * log.
     */
    private static final Logger log = LoggerFactory.getLogger(ServicioPropietarioActivo.class);

    /**
     * PropietarioActivoRepositorio.
     */
    @Autowired
    private PropietarioActivoRepositorio repoPropietarioActivo;
    /**
     * ActivoRepositorio.
     */
    @Autowired
    private ActivoRepositorio repoActivo;
    /**
     * AreaRepositorio.
     */
    @Autowired
    private AreaRepositorio repoArea;
    /**
     * PersonaRepositorio.
     */
    @Autowired
    private PersonaRepositorio repoPersona;

    /**
     * Método que consulta en la base de datos un Propietario de un Activo por
     * su Id.
     *
     * @param id Id.
     * @return PropietarioActivo
     */
    public Optional<PropietarioActivo> buscarPropietarioActivoById(Integer id) {
        Optional<PropietarioActivo> respuesta = Optional.empty();
        try {
            log.info("id_propietario:" + id);
            respuesta = Optional.of(repoPropietarioActivo.findById(id));
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que consulta en la base de datos un Propietario de un Activo por
     * el Id del Activo.
     *
     * @param id Id.
     * @return PropietarioActivo
     */
    public PropietarioActivo buscarPropietarioActivoByIdActivo(Integer id) {
        PropietarioActivo respuesta = null;
        try {
            Activo activo = new Activo();
            activo.setId(id);
            respuesta = repoPropietarioActivo.findByIdActivo(activo);
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que permite crear una relación de un activo con su propietario en
     * la base de datos.
     *
     * @param nuevoPropietario Nuevo propietario.
     * @return PropietarioActivo
     */
    public Optional<PropietarioActivo> crear(NuevoPropietario nuevoPropietario) {
        Optional<PropietarioActivo> respuesta = Optional.empty();
        PropietarioActivo propietarioActividad = null;
        Optional<Activo> activoAsignar = Optional.empty();
        Optional<Persona> personaPropietario = Optional.empty();
        Optional<Area> areaPropietario = Optional.empty();

        //logger.info("act:"+idActivo+" per:"+idPersona+" are:"+idArea);
        try {
            activoAsignar = Optional.of(repoActivo.findById(nuevoPropietario.getIdActivo()).get());

            if (activoAsignar.isPresent()) {

                propietarioActividad = repoPropietarioActivo.findByIdActivo(activoAsignar.get());
                if (propietarioActividad == null) {
                    log.info("El activo exite!");
                    propietarioActividad = new PropietarioActivo();
                    propietarioActividad.setIdActivo(activoAsignar.get());
                    propietarioActividad.setFechaAsignacion(new Date());

                    if (nuevoPropietario.getIdPersona() != null) {
                        personaPropietario = Optional.of(repoPersona.findById(nuevoPropietario.getIdPersona()).get());
                    }
                    if (nuevoPropietario.getIdArea() != null) {
                        areaPropietario = Optional.of(repoArea.findById(nuevoPropietario.getIdArea()).get());
                    }

                    if (!personaPropietario.isEmpty()) {
                        log.info("La persona exite!");
                        propietarioActividad.setIdPersona(personaPropietario.get());
                    }
                    if (!areaPropietario.isEmpty()) {
                        log.info("El area exite!");
                        propietarioActividad.setIdArea(areaPropietario.get());
                    }
                    respuesta = Optional.of(repoPropietarioActivo.save(propietarioActividad));
                } else {
                    log.info("El activo ya tiene un propietario");
                }
            } else {
                log.info("El activo no existe!");
            }
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }
}
