/*
* Archivo: HttpNoContentExecption.java
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
 * HttpNoContentExecption.
 *
 * @author Santiago Rojas Manios
 */
public class HttpNoContentExecption extends RuntimeException {

    /**
     * Constructor.
     *
     * @param recursoNombre Nombre del recurso.
     * @param recursoValor Valor del recurso.
     */
    public HttpNoContentExecption(String recursoNombre, String recursoValor) {
        super("Sin información, " + recursoNombre + " con valor:" + recursoValor + ", no se ha encontrado.");
    }

    /**
     * Constructor.
     *
     * @param message Mensaje de la excepcion.
     */
    public HttpNoContentExecption(String message) {
        super("Sin información, " + message + ", no se ha encontrado.");
    }

    /**
     * Constructor.
     *
     * @param message Mensaje de la excepcion.
     * @param cause Excepcion raiz.
     */
    public HttpNoContentExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
