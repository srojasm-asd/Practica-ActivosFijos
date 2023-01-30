/*
* Archivo: ServicioTipoActivo.java
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

import com.grupoasd.activos.entity.TipoActivo;
import com.grupoasd.activos.repository.TipoActivoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que inyecta de la capa de repositorio de la tabla Tipo_Activo, para
 * permitir implementar los metodos a nivel de capa de servicio.
 *
 * @author Santiago Rojas Manios
 *
 */
@Slf4j
@Service
public class ServicioTipoActivo {

    /**
     * TipoActivoRepositorio.
     */
    @Autowired
    private TipoActivoRepositorio repoTipoActivo;

    /**
     * Buscar tipo activo por ID.
     *
     * @param id ID.
     * @return TipoActivo.
     */
    public Optional<TipoActivo> buscarTipoActivoById(Integer id) {
        Optional<TipoActivo> respuesta = Optional.empty();
        try {
            respuesta = Optional.of(repoTipoActivo.findById(id));
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Buscar todos los tipos de activos.
     *
     * @return List-TipoActivo.
     */
    public List<TipoActivo> buscarTipoActivos() {
        List<TipoActivo> respuesta = new ArrayList<>();
        try {
            respuesta = repoTipoActivo.findAll();
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }
}
