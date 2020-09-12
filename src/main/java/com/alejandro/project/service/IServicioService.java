package com.alejandro.project.service;

import com.alejandro.project.model.Servicio;

import java.util.List;

public interface IServicioService {
    public void agregarServicio(Servicio servicio);
    public List<Servicio> listarServicios();
}
