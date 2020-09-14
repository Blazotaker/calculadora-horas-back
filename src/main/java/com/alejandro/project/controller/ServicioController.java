package com.alejandro.project.controller;

import com.alejandro.project.exception.ResponseCalculoHoras;
import com.alejandro.project.exception.ResponseCreate;
import com.alejandro.project.model.Servicio;
import com.alejandro.project.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ServicioController {

    private final IServicioService servicioService;

    @Autowired
    ServicioController(IServicioService servicio){
        this.servicioService = servicio;
    }

    @PostMapping("/servicios")
    public ResponseEntity<ResponseCreate> agregarServicio(@RequestBody Servicio servicio){
         return ResponseEntity.ok(servicioService.agregarServicio(servicio));
    }

    @GetMapping("/timestamp")
    public Servicio test (@RequestBody Servicio servicio){
        return servicio;
    }

    /*@GetMapping("/test")
    public List<Servicio> calcularSemana(){
        return servicioService.traerTodo();
        *//*LocalDate.of( 2020 , 12 , 31 ).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);*//*
    }*/

    @GetMapping("/servicios/{idTecnico}/semana/{semana}")
    public ResponseEntity<ResponseCalculoHoras> horasSemanalesPorTecnico
            (@PathVariable String idTecnico, @PathVariable int semana){
        return ResponseEntity.ok(servicioService.horasSemanalesPorTecnico(idTecnico,semana));
    }




}
