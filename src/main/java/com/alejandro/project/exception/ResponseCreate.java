package com.alejandro.project.exception;

import com.alejandro.project.model.Servicio;
import lombok.Data;

@Data
public class ResponseCreate {
    private Servicio servicio;
    private String error;
}
