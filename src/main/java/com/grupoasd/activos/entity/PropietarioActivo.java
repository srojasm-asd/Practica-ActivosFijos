/*
* Archivo: PropietarioActivo.java
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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * Clase que representa la entidad de base de datos Propietario_Activo.
 *
 * @author Santiago Rojas Manios
 *
 */
@Data
@Entity
@Table(name = "Propietario_Activo")
public class PropietarioActivo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propietario_activo_generador")
    @SequenceGenerator(name = "propietario_activo_generador",
            sequenceName = "sq_propietario_activo", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    /**
     * Fecha Asignacion.
     */
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    /**
     * Fecha Retiro.
     */
    @Column(name = "fecha_retiro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetiro;
    /**
     * Id Activo.
     */
    @JoinColumn(name = "id_activo", referencedColumnName = "id")
    @ManyToOne
    private Activo idActivo;
    /**
     * Id Area.
     */
    @JoinColumn(name = "id_area", referencedColumnName = "id")
    @ManyToOne
    private Area idArea;
    /**
     * Id Persona.
     */
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    @ManyToOne
    private Persona idPersona;

    @Override
    public String toString() {
        return "PropietarioActivo[ id=" + id + " ]";
    }

}
