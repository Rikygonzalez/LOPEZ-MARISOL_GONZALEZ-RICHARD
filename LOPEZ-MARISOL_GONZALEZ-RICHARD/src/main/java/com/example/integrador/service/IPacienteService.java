package com.example.integrador.service;

import com.example.integrador.dto.PacienteDto;
import com.example.integrador.entity.Paciente;
import com.example.integrador.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    List<PacienteDto> listarPaciente();
    PacienteDto buscarPacientePorId(Long id);
    PacienteDto registrarPaciente(Paciente paciente);
    PacienteDto actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
