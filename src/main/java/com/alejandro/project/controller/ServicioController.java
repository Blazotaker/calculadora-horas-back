package com.alejandro.project.controller;

import com.alejandro.project.exception.ResponseCalculoHoras;
import com.alejandro.project.exception.ResponseCrear;
import com.alejandro.project.model.Servicio;
import com.alejandro.project.service.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ServicioController {

    private final IServicioService servicioService;

    @Autowired
    ServicioController(IServicioService servicio){
        this.servicioService = servicio;
    }

    @PostMapping("/servicios")
    public ResponseEntity<ResponseCrear> agregarServicio(@RequestBody Servicio servicio){
         return ResponseEntity.ok(servicioService.agregarServicio(servicio));
    }

    @GetMapping("/servicios/{idTecnico}/semana/{semana}")
    public ResponseEntity<ResponseCalculoHoras> horasSemanalesPorTecnico
            (@PathVariable String idTecnico, @PathVariable int semana){
        return ResponseEntity.ok(servicioService.horasSemanalesPorTecnico(idTecnico,semana));
    }




}
