/*
* Archivo: ServiceActivoTest.java
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
package com.grupoasd.activos.service;

import com.grupoasd.activos.api.ActivoController;
import com.grupoasd.activos.entity.Activo;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Pruenas unitarias para el servicio de la clase Activos..
 *
 * @author Santiago Rojas Manios
 */
@ExtendWith(MockitoExtension.class)
public class ServiceActivoTest {

    @BeforeEach
    public void init() {

    }

    @InjectMocks
    private ActivoController activoApiController;

    @Mock
    private ServicioActivo activoServiceImpl;

    @Test
    @DisplayName("Listar activos.")
    public void consultarActivos() {
        List<Activo> resultado = new ArrayList();
        when(activoServiceImpl.buscarTodosLosActivos()).thenReturn(resultado);
        ResponseEntity<?> res = activoApiController.consultarActivos();
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

}
