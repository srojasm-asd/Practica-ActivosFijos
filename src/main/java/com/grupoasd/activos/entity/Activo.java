/*
* Archivo: Activo.java
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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Clase que representa la entidad de base de datos Activo.
 *
 * @author Santiago Rojas Manios
 *
 */
@Data
@Entity
@Table(name = "Activo")
public class Activo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activo_generador")
    @SequenceGenerator(name = "activo_generador", sequenceName = "sq_activo", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    /**
     * Serial.
     */
    @NotNull
    @Column(name = "serial")
    private String serial;
    /**
     * Nombre.
     */
    @NotNull
    @Column(name = "nombre")
    private String nombre;
    /**
     * Descripcion.
     */
    @Column(name = "descripcion")
    private String descripcion;
    /**
     * Valor Compra.
     */
    @Column(name = "valor_compra")
    private BigInteger valorCompra;
    /**
     * Fecha Compra.
     */
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    /**
     * Fecha Baja.
     */
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    /**
     * Peso.
     */
    @Column(name = "peso")
    private Integer peso;
    /**
     * Color.
     */
    @Column(name = "color")
    private String color;
    /**
     * Alto.
     */
    @Column(name = "alto")
    private Integer alto;
    /**
     * Ancho.
     */
    @Column(name = "ancho")
    private Integer ancho;
    /**
     * Largo.
     */
    @Column(name = "largo")
    private Integer largo;
    /**
     * Id Estado Activo.
     */
    @NotNull
    @JsonProperty("estado")
    @JoinColumn(name = "id_estado_activo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoActivo idEstadoActivo;
    /**
     * Id Tipo Activo.
     */
    @NotNull
    @JsonProperty("tipo")
    @JoinColumn(name = "id_tipo_activo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoActivo idTipoActivo;

    @Override
    public String toString() {
        return "Activo[ id=" + id + " ]";
    }

}
