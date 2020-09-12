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
import java.util.List;

@Repository
public class ServicioDaoImpl implements IServicioDao {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public ServicioDaoImpl(NamedParameterJdbcTemplate template) throws DataAccessException {
        this.template = template;
    }

    private SqlParameterSource getParametros(Servicio servicio){
        MapSqlParameterSource params = new MapSqlParameterSource();
        if(servicio != null){
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            params.addValue("id_tecnico", servicio.getIdTecnico());
            params.addValue("id_tipo_servicio", servicio.getIdTipoServicio());
            params.addValue("fecha_inicio", servicio.getFechaInicio());
            params.addValue("fecha_fin", servicio.getFechaFin());
            params.addValue("creado", formatoFecha.format(timestamp));
        }
        return params;
    }

    @Override
    public void agregarServicio(Servicio servicio) throws DataAccessException  {
        String query = "INSERT INTO tbl_servicio (id_tecnico,id_tipo_servicio,fecha_inicio,fecha_fin,creado)" +
                "VALUES (:id_tecnico,:id_tipo_servicio,:fecha_inicio,:fecha_fin,:creado)";
        System.out.println(getParametros(servicio));
        template.update(query,getParametros(servicio));
    }

    @Override
    public List<Servicio> listarServicios(){
        String query = "SELECT * from tbl_servicio";
        ServicioMapper  m  = new ServicioMapper();
        return template.query(query,m);
    }


}
