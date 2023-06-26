package com.example.integrador.service;

import com.example.integrador.dto.TurnoDto;
import com.example.integrador.entity.Turno;
import com.example.integrador.exceptions.BadRequestException;
import com.example.integrador.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoDto registrarTurno(Turno turno) throws BadRequestException;

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(Long id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(Long id) throws ResourceNotFoundException;


}