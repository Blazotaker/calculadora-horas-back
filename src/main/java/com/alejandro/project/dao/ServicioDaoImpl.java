package com.alejandro.project.dao;

import com.alejandro.project.dao.mapper.ServicioMapper;
import com.alejandro.project.model.Servicio;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class ServicioDaoImpl implements IServicioDao {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public ServicioDaoImpl(NamedParameterJdbcTemplate template) throws DataAccessException {
        this.template = template;
    }

    private SqlParameterSource getParametrosInsertar(Servicio servicio){
        MapSqlParameterSource params = new MapSqlParameterSource();
        if(servicio != null){
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            params.addValue("id_tecnico", servicio.getIdTecnico());
            params.addValue("id_tipo_servicio", servicio.getIdTipoServicio());
            params.addValue("fecha_inicio", servicio.getFechaInicio());
            params.addValue("fecha_fin", servicio.getFechaFin());
        }
        return params;
    }

    @Override
    public Servicio  agregarServicio(Servicio servicio) throws DataAccessException  {
        HashMap<String, String> respuesta = new HashMap<>();
        String query ="";
        if(buscarPorServicio(servicio)){
           query = "INSERT INTO tbl_servicio (id_tecnico,id_tipo_servicio,fecha_inicio,fecha_fin,creado)" +
                    "VALUES (:id_tecnico,:id_tipo_servicio,:fecha_inicio,:fecha_fin,current_timestamp)";
            System.out.println("Cree");
        }else{
            query = " UPDATE tbl_servicio set id_tecnico = :id_tecnico," +
                    " id_tipo_servicio = :id_tipo_servicio ," +
                    " fecha_inicio = :fecha_inicio," +
                    " fecha_fin = :fecha_fin  WHERE " +
                    " id_tecnico = :id_tecnico AND" +
                    " id_tipo_servicio = :id_tipo_servicio AND fecha_inicio = :fecha_inicio AND fecha_fin = :fecha_fin";
            System.out.println("Actualicé");
        }
        template.update(query,getParametrosInsertar(servicio));
        return servicio;


    }



    @Override
    public List<Servicio> listarServiciosPorTecnico(String idTecnico){
        String query = "SELECT id_tecnico,id_tipo_servicio,fecha_inicio,fecha_fin" +
                " from tbl_servicio WHERE id_tecnico = :id_tecnico";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_tecnico", idTecnico);
        ServicioMapper  mapper  = new ServicioMapper();
        return template.query(query,params,mapper);
    }


    public boolean buscarPorServicio(Servicio servicio){
        String query = "SELECT id_tecnico,id_tipo_servicio,fecha_inicio,fecha_fin from tbl_servicio WHERE" +
                " id_tecnico = :id_tecnico AND" +
                " id_tipo_servicio = :id_tipo_servicio AND fecha_inicio = :fecha_inicio AND fecha_fin = :fecha_fin" +
                " LIMIT 10";
        ServicioMapper  mapper  = new ServicioMapper();
        if(template.query(query,getParametrosInsertar(servicio),mapper).isEmpty()){
            return true;
        }
        return false;
    }

    /*@Override
    public List<Servicio> todo() {
        String query = "SELECT * from tbl_servicio";
        ServicioMapper  m  = new ServicioMapper();
        return template.query(query,m);
    }*/


}
