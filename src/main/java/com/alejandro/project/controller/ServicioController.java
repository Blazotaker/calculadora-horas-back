package com.alejandro.project.controller;

import com.alejandro.project.model.Servicio;
import com.alejandro.project.service.IServicioService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ServicioController {

    private final IServicioService servicioService;

    @Autowired
    ServicioController(IServicioService servicio){
        this.servicioService = servicio;
    }

    @PostMapping("/servicios")
    public Map<String, Boolean> agregarServicio(@RequestBody Servicio servicio){
        servicioService.agregarServicio(servicio);
        Map<String, Boolean> response = new HashMap<>();
        response.put("creado", Boolean.TRUE);
        return response;
    }

    @GetMapping("/timestamp")
    public Servicio test (@RequestBody Servicio servicio){
        return servicio;
    }

    @GetMapping("/servicios")
    public ResponseEntity<List<Servicio>> listarServicios(){
        return ResponseEntity.ok(servicioService.listarServicios());
    }
}
