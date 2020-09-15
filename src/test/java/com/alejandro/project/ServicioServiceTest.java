package com.alejandro.project;

import com.alejandro.project.dao.IServicioDao;
import com.alejandro.project.exception.ResponseCalculoHoras;
import com.alejandro.project.exception.ResponseCrear;
import com.alejandro.project.model.Servicio;
import com.alejandro.project.service.IServicioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
public class ServicioServiceTest {

    private IServicioService servicioService;

    @Autowired
    public ServicioServiceTest(IServicioService servicioService){
        this.servicioService = servicioService;
    }

    @Test
    public void agregarExitoTest(){
        Servicio servicio = new Servicio();
        servicio.setIdTecnico("Tecnico 1");
        servicio.setIdTipoServicio("Tipo 1");
        servicio.setFechaInicio(LocalDateTime.of(2020,9,15,8,50));
        servicio.setFechaFin(LocalDateTime.of(2020,9,15,19,50));
        ResponseCrear esperado = new ResponseCrear();
        esperado.setServicio(servicio);
        ResponseCrear resultado = servicioService.agregarServicio(servicio);
        resultado.setServicio(servicio);
        Assertions.assertEquals(resultado,esperado);
    }



    @Test
    public void consultarExitoTest(){
        ResponseCalculoHoras resultado = servicioService.horasSemanalesPorTecnico("Tecnico 1", 39);
        ResponseCalculoHoras esperado = new ResponseCalculoHoras();
        HashMap<String, Integer> horas = new HashMap<>();
        horas.put("HorasNormales", 11);
        horas.put("HorasNocturnas", 0);
        horas.put("HorasDominicales",0);
        horas.put("HorasNormalesExtra",0);
        horas.put("HorasNocturnasExtra",0);
        horas.put("HorasDominicalesExtra",0);
        horas.put("TotalHoras",11);
        esperado.setHoras(horas);
        Assertions.assertEquals(resultado,esperado);

    }

    @Test
    public void consultarFallidoTest(){
        ResponseCalculoHoras resultado = servicioService.horasSemanalesPorTecnico("Tecnico 1", 50);
        ResponseCalculoHoras esperado = new ResponseCalculoHoras();
        HashMap<String, Integer> horas = new HashMap<>();
        horas.put("HorasNormales", 0);
        horas.put("HorasNocturnas", 0);
        horas.put("HorasDominicales",0);
        horas.put("HorasNormalesExtra",0);
        horas.put("HorasNocturnasExtra",0);
        horas.put("HorasDominicalesExtra",0);
        horas.put("TotalHoras",0);
        esperado.setHoras(horas);
        Assertions.assertEquals(resultado,esperado);
    }



}
