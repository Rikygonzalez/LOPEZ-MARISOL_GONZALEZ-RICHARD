package com.backend.dao.impl;

import com.backend.dao.H2Connection;
import com.backend.dao.IDao;
import entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGO (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                odontologo.setId(rs.getLong(1));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el odontologo: " + odontologo);

        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception){
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception e){
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + e.getMessage());
                e.printStackTrace();
            }
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos= new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGO");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Odontologo odontologo = crearObjetoOdontologo(rs);
                odontologos.add(odontologo);
            }

            LOGGER.info("Listado de todos los odontologos: " + odontologos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologos;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
        Long idOdontologo = resultSet.getLong("id");
        int numeroMatricula = resultSet.getInt("numero_matricula");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        return new Odontologo(idOdontologo, numeroMatricula, nombre, apellido);
    }
}
