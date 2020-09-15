package com.alejandro.project.service;

import com.alejandro.project.exception.ResponseCalculoHoras;
import com.alejandro.project.exception.ResponseCrear;
import com.alejandro.project.model.Servicio;

public interface IServicioService {
    public ResponseCrear agregarServicio(Servicio servicio);
    public ResponseCalculoHoras horasSemanalesPorTecnico(String idTecnico, int semana);

}
