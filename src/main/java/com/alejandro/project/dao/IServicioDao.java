package com.alejandro.project.dao;

import com.alejandro.project.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IServicioDao  {

    public Servicio agregarServicio(Servicio servicio);

    public List<Servicio> listarServiciosPorTecnico(String idTecnico);

    //public boolean buscarPorServicio (Servicio servicio);
    //public List<Servicio> todo ();

}
