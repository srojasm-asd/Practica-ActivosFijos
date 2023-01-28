/*
* Archivo: CorsConfig.java
* Fecha: 27/01/2023
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
package com.grupoasd.activos.config;

import com.grupoasd.activos.util.GlobalConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracion de CORS.
 *
 * @author Juan Carlos Castellanos jccastellanos@grupoasd.com.co
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Perfil de entorno de la aplicacion.
     */
    @Value("${spring.profiles.active}")
    private String activeProfile;

    /**
     * Permite habilitar el CORS desde cualquier origen en enterno de
     * desarrollo. En entorno productivo el CORS debe ser manejado por el API
     * Gateway.
     *
     * @param registry CorsRegistry.
     * @author Juan Carlos Castellanos jccastellanos@grupoasd.com.co
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (GlobalConstants.PROFILE_DEV.equals(activeProfile)) {
            registry.addMapping("/**");
        }
    }

}
