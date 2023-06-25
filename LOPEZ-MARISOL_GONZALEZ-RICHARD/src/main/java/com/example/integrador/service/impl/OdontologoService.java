package com.example.integrador.service.impl;

import com.example.integrador.dto.OdontologoDto;
import com.example.integrador.entity.Odontologo;
import com.example.integrador.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) {
        return null;
    }

    @Override
    public List<OdontologoDto> listarOdontologos() {
        return null;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        return null;
    }

    @Override
    public void eliminarOdontologo(Long id) {

    }
}
