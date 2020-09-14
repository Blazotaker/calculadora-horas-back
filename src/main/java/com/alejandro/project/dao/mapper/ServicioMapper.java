package com.alejandro.project.dao.mapper;

import com.alejandro.project.model.Servicio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioMapper implements RowMapper<Servicio> {
    @Override
    public Servicio mapRow(ResultSet resultSet, int i) throws SQLException {
        Servicio servicio = new Servicio();
        servicio.setIdTecnico(resultSet.getString("id_tecnico"));
        servicio.setIdTipoServicio(resultSet.getString("id_tipo_servicio"));
        servicio.setFechaInicio(resultSet.getTimestamp("fecha_inicio").toLocalDateTime());
        servicio.setFechaFin(resultSet.getTimestamp("fecha_fin").toLocalDateTime());
        /*resultSet.getLong("id_servicio"),
        resultSet.getInt("id_tecnico"),
                resultSet.getInt("id_tecnico"),
                resultSet.getTimestamp("fecha_inicio").toLocalDateTime(),
                resultSet.getTimestamp("fecha_fin").toLocalDateTime(),
                resultSet.getTimestamp("creado")*/
        return servicio;
    }
}
