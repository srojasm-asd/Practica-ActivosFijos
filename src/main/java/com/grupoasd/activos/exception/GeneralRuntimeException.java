/*
* Archivo: GeneralRuntimeException.java
* Fecha: 28/01/2023
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
package com.grupoasd.activos.exception;

/**
 * Manejo de errores generales de la aplicación.
 *
 * @author Santiago Rojas Manios
 */
public class GeneralRuntimeException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Mensaje de la excepcion.
     */
    public GeneralRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message Mensaje de la excepcion.
     * @param cause Excepcion raiz.
     */
    public GeneralRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
