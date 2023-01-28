/*
* Archivo: ServicioPersona.java
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

import com.grupoasd.activos.entity.Persona;
import com.grupoasd.activos.repository.PersonaRepositorio;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que inyecta de la capa de repositorio de la tabla Persona, para
 * permitir implementar los metodos a nivel de capa de servicio.
 *
 * @author Santiago Rojas Manios
 *
 */
@Slf4j
@Service
public class ServicioPersona {

    /**
     * PersonaRepositorio.
     */
    @Autowired
    private PersonaRepositorio repoPersona;

    /**
     * Método que consulta en la base de datos todas las Persona.
     *
     * @return List-Persona
     */
    public List<Persona> buscarTodosLasPersonas() {
        List<Persona> respuesta = null;
        try {
            respuesta = repoPersona.findAll();
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }

    /**
     * Método que consulta en la base de datos una Persona por su Id.
     *
     * @param id Id.
     * @return Persona
     */
    public Optional<Persona> buscarPersonaById(Integer id) {
        Optional<Persona> respuesta = Optional.empty();
        try {
            respuesta = repoPersona.findById(id);
            return respuesta;
        } catch (Exception e) {
            log.info(e.getMessage());
            return respuesta;
        }
    }
}
