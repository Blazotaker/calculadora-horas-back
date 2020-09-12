package com.alejandro.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import com.google.common.base.Preconditions;
import lombok.Value;

import java.util.Date;

/*@Value(staticConstructor = "from")*/
@Data
public class Servicio implements Serializable {
    private Long idServicio;
    private String idTecnico;
    private String idTipoServicio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Timestamp creado;


    /*public Servicio(Long idServicio, int idTecnico, int idTipoServicio, LocalDateTime fechaInicio, LocalDateTime fechaFin, Timestamp creado) {
        Preconditions.checkNotNull(idTecnico,"El campo id tecnico no puede ser nulo");
        Preconditions.checkNotNull(idTipoServicio,"El campo id tipo servicio no puede ser nulo");
        Preconditions.checkNotNull(fechaInicio,"El campo fecha iniciono puede ser nulo");
        Preconditions.checkNotNull(fechaFin,"El campo no puede ser nulo");
        this.idServicio = idServicio;
        this.idTecnico = idTecnico;
        this.idTipoServicio = idTipoServicio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.creado = creado;
    }*/
}
