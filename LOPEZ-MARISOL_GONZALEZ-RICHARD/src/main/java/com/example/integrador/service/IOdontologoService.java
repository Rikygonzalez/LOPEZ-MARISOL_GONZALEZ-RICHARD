package com.example.integrador.service;

import com.example.integrador.dto.OdontologoDto;
import com.example.integrador.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id);
    List<OdontologoDto>listarOdontologos();
    OdontologoDto actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
}
