package com.backend.dao.impl;

import com.backend.dao.IDao;
import entity.Odontologo;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {

    private List<Odontologo> repositorio = new ArrayList<>();

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        repositorio.add(odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return repositorio;
    }
}