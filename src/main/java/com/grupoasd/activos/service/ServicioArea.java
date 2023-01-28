/*
* Archivo: ServicioArea.java
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

import com.grupoasd.activos.entity.Area;
import com.grupoasd.activos.repository.AreaRepositorio;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que inyecta de la capa de repositorio de la tabla Area, para permitir
 * implementar los metodos a nivel de capa de servicio.
 *
 * @author Santiago Rojas Manios
 *
 */
@Slf4j
@Service
public class ServicioArea {

    /**
     * AreaRepositorio.
     */
    @Autowired
    private AreaRepositorio repoArea;

    /**
     * Método que consulta en la base de datos todos los Activos.
     *
     * @return List-Area
     */
    public List<Area> buscarTodosLasAreas() {
        List<Area> respuesta = null;
        try {
            respuesta = repoArea.findAll();
            // logger.info(respuesta.size()+"");
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que consulta en la base de datos un Area por su Id.
     *
     * @param id Id.
     * @return Area
     */
    public Optional<Area> buscarAreaById(Integer id) {
        Optional<Area> respuesta = Optional.empty();
        try {
            respuesta = repoArea.findById(id);
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }
}
