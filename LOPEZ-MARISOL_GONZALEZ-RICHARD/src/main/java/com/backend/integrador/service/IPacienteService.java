package com.backend.integrador.service;

import com.backend.integrador.dto.PacienteDto;
import com.backend.integrador.entity.Paciente;
import com.backend.integrador.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    List<PacienteDto> listarPaciente();
    PacienteDto buscarPacientePorId(Long id);
    PacienteDto registrarPaciente(Paciente paciente);
    PacienteDto actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
