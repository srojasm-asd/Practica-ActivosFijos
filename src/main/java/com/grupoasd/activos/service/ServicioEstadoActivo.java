/*
* Archivo: ServicioEstadoActivo.java
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

import com.grupoasd.activos.entity.EstadoActivo;
import com.grupoasd.activos.repository.EstadoActivoRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que inyecta de la capa de repositorio de la tabla Estado_Activo, para
 * permitir implementar los metodos a nivel de capa de servicio.
 *
 * @author Santiago Rojas Manios
 *
 */
@Slf4j
@Service
public class ServicioEstadoActivo {

    /**
     * EstadoActivoRepositorio.
     */
    @Autowired
    private EstadoActivoRepositorio repoEstadoActivo;

    /**
     * Buscar estado activo por id.
     *
     * @param id Id.
     * @return EstadoActivo.
     */
    public EstadoActivo buscarEstadoActivoById(Integer id) {
        EstadoActivo respuesta = null;
        try {
            respuesta = repoEstadoActivo.findById(id);
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }
}
