/*
* Archivo: NuevoPropietario.java
* Fecha: 06/02/2023
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
package com.grupoasd.activos.model;

import lombok.Data;

/**
 * Objeto que representa un nuevo propietario.
 *
 * @author Santiago Rojas Manios
 *
 */
@Data
public class NuevoPropietario {

    private Integer idActivo;
    private Integer idArea;
    private Integer idPersona;
}
