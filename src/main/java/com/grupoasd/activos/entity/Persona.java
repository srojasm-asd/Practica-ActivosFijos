/*
* Archivo: Persona.java
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
package com.grupoasd.activos.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

/**
 * Clase que representa la entidad de base de datos Persona.
 *
 * @author Santiago Rojas Manios
 *
 */
@Data
@Entity
@Table(name = "Persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_generador")
    @SequenceGenerator(name = "persona_generador", sequenceName = "sq_persona", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    /**
     * Nombre.
     */
    @Column(name = "nombre")
    private String nombre;
    /**
     * Cargo.
     */
    @Column(name = "cargo")
    private String cargo;

    @Override
    public String toString() {
        return "Persona[ id=" + id + " ]";
    }

}
