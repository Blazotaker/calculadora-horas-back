package com.alejandro.project.service;

import com.alejandro.project.exception.ResponseCalculoHoras;
import com.alejandro.project.exception.ResponseCreate;
import com.alejandro.project.model.Servicio;

import java.util.HashMap;
import java.util.List;

public interface IServicioService {
    public ResponseCreate agregarServicio(Servicio servicio);
    public ResponseCalculoHoras horasSemanalesPorTecnico(String idTecnico, int semana);
    //public List<Servicio> traerTodo();

}
