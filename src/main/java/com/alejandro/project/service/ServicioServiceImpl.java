package com.alejandro.project.service;

import com.alejandro.project.dao.IServicioDao;
import com.alejandro.project.exception.ResponseCalculoHoras;
import com.alejandro.project.exception.ResponseCreate;
import com.alejandro.project.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.IsoFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ServicioServiceImpl implements  IServicioService{

    private IServicioDao servicioDao;

    @Autowired
    public ServicioServiceImpl(IServicioDao servicioDao){
        this.servicioDao = servicioDao;
    }



    @Override
    public ResponseCreate agregarServicio(Servicio servicio) {
        ResponseCreate respuesta = new ResponseCreate();
        if(servicio.getIdTecnico() == null ||
            servicio.getIdTipoServicio() == null ||
                servicio.getFechaInicio() == null ||
                servicio.getFechaFin() == null
        ){
            respuesta.setError("No se aceptan campos nulos");
            return respuesta;
        }
            respuesta.setServicio(servicioDao.agregarServicio(servicio));
            return respuesta;


    }

    @Override
    public ResponseCalculoHoras horasSemanalesPorTecnico (String idTecnico, int semana) {
        ResponseCalculoHoras respuesta = new ResponseCalculoHoras();
        if(idTecnico.equalsIgnoreCase("null")){
            respuesta.setError("No se aceptan campos nulos");
            return respuesta;
        }else if (semana == 0){
            respuesta.setError("Las semanas no pueden empezar en 0");
            return respuesta;
        }
        respuesta.setHoras( listarPorSemana(servicioDao.listarServiciosPorTecnico(idTecnico), semana));
        return respuesta;
    }

   /* @Override
    public List<Servicio> traerTodo() {
        return servicioDao.todo();
    }*/

    public  HashMap<String, Integer> listarPorSemana(List <Servicio> servicios,int semana){
        List <Servicio> serviciosSemana =
         servicios.stream()
                .filter(a -> (LocalDate.of(
                          a.getFechaFin().getYear()
                        , a.getFechaInicio().getMonthValue()
                        , a.getFechaInicio().getDayOfMonth())
                        .get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) == semana-1))
                 .sorted(Comparator.comparing(Servicio::getFechaInicio))
                .collect(Collectors.toList());
         return calcularHoras(serviciosSemana);
    }

    public boolean validarHoraExtra(int totalHoras){
        return totalHoras >= 48;
    }

    public void  almacenarHora(HashMap<String, Integer> total, String tipo){
         total.replace(tipo, total.get(tipo)+1);
         //return total;
    }

    public  HashMap<String, Integer> calcularHoras(List <Servicio> serviciosSemana){
        HashMap<String, Integer> resultado = new HashMap<>();
        resultado.put("HorasNormales", 0);
        resultado.put("HorasNocturnas", 0);
        resultado.put("HorasDominicales",0);
        resultado.put("HorasNormalesExtra",0);
        resultado.put("HorasNocturnasExtra",0);
        resultado.put("HorasDominicalesExtra",0);
        resultado.put("TotalHoras",0);
        LocalTime inicioJornada = LocalTime.of(7,0);
        LocalTime finJornada = LocalTime.of(20,0);

        serviciosSemana.forEach(data -> {
            Duration p = Duration.between(data.getFechaInicio(), data.getFechaFin());
            long horas = Math.abs(p.toHours());
            System.out.println(data);

            IntStream.range(0,(int) horas).forEach(i -> {
                System.out.println(i);
                LocalDateTime horasAEvaluar = data.getFechaInicio().plusHours(i);
                if(validarHoraExtra(resultado.get("TotalHoras"))){
                    //Horas > 48
                    if(horasAEvaluar.getDayOfWeek().toString().equalsIgnoreCase("sunday")){
                        //Hora dominical
                        System.out.println("Soy una hora dominical extra");
                        //resultado.replace("HorasDominicalesExtra", resultado.get("HorasDominicalesExtra")+1);
                        almacenarHora(resultado,"HorasDominicalesExtra");
                    }else if(( horasAEvaluar.isAfter(LocalDateTime.of(horasAEvaluar.toLocalDate(),inicioJornada))  ||
                                horasAEvaluar.isEqual(LocalDateTime.of(horasAEvaluar.toLocalDate(),inicioJornada))) &&
                                horasAEvaluar.isBefore(LocalDateTime.of(horasAEvaluar.toLocalDate(),finJornada))){
                        System.out.println("Soy una hora normal extra");
                        //resultado.replace("HorasNormalesExtra", resultado.get("HorasNormalesExtra")+1);
                        almacenarHora(resultado,"HorasNormalesExtra");

                    }else if(horasAEvaluar.isAfter(LocalDateTime.of(horasAEvaluar.toLocalDate(),finJornada)) ||
                                    horasAEvaluar.isEqual(LocalDateTime.of(horasAEvaluar.toLocalDate(),finJornada)) &&
                                    horasAEvaluar.isBefore(LocalDateTime.of(horasAEvaluar.toLocalDate().plusDays(1),inicioJornada))){
                        System.out.println("Soy una hora nocturna extra");
                        //resultado.replace("HorasNocturnasExtra", resultado.get("HorasNocturnasExtra")+1);
                        almacenarHora(resultado,"HorasNocturnasExtra");

                    }
                }else if(horasAEvaluar.getDayOfWeek().toString().equalsIgnoreCase("sunday")){
                    //Hora dominical
                    System.out.println("Soy una hora dominical trabajo");
                    //resultado.replace("HorasDominicales", resultado.get("HorasDominicales")+1);
                    almacenarHora(resultado,"HorasDominicales");

                } else if( ( horasAEvaluar.isAfter(LocalDateTime.of(horasAEvaluar.toLocalDate(),inicioJornada)) ||
                        horasAEvaluar.isEqual(LocalDateTime.of(horasAEvaluar.toLocalDate(),inicioJornada))) &&
                        horasAEvaluar.isBefore(LocalDateTime.of(horasAEvaluar.toLocalDate(),finJornada)) ){
                    System.out.println("Soy una hora normal");
                    //resultado.replace("HorasNormales", resultado.get("HorasNormales")+1);
                    almacenarHora(resultado,"HorasNormales");

                } else if((horasAEvaluar.isAfter(LocalDateTime.of(horasAEvaluar.toLocalDate(),finJornada)) ||
                        horasAEvaluar.isEqual(LocalDateTime.of(horasAEvaluar.toLocalDate(),finJornada))) &&
                        horasAEvaluar.isBefore(LocalDateTime.of(horasAEvaluar.toLocalDate().plusDays(1),inicioJornada)) ){
                    System.out.println("Soy una hora nocturna");
                   // resultado.replace("HorasNocturnas", resultado.get("HorasNocturnas")+1);
                    almacenarHora(resultado,"HorasNocturnas");

                }
                //resultado.replace("TotalHoras", resultado.get("TotalHoras")+1);
                almacenarHora(resultado,"TotalHoras");
                System.out.println(resultado);
            });
        });



        return resultado;
    }

}
