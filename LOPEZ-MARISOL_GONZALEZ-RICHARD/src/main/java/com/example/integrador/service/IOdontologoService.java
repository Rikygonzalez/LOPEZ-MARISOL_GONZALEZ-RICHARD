package com.example.integrador.service;

import com.example.integrador.dto.OdontologoDto;
import com.example.integrador.entity.Odontologo;
import com.example.integrador.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto registrarOdonotologo(Odontologo odontologo);

    OdontologoDto buscarOdontologoPorId(Long id);
    List<OdontologoDto>listarOdontologos();
    OdontologoDto actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
