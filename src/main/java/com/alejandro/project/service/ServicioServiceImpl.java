package com.alejandro.project.service;

import com.alejandro.project.dao.IServicioDao;
import com.alejandro.project.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements  IServicioService{

    @Autowired
    private IServicioDao servicioDao;


    @Override
    public void agregarServicio(Servicio servicio) {
        servicioDao.agregarServicio(servicio);
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioDao.listarServicios();
    }


}
