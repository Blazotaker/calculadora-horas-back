package com.alejandro.project.model;


import lombok.Data;

import java.io.Serializable;

import java.time.LocalDateTime;


/*@Value(staticConstructor = "from")*/
@Data
public class Servicio implements Serializable {
    private Long idServicio;
    private String idTecnico;
    private String idTipoServicio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalDateTime creado;



}
