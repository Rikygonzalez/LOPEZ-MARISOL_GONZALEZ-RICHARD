package com.example.integrador.service.impl;

import com.example.integrador.dto.OdontologoDto;
import com.example.integrador.dto.PacienteDto;
import com.example.integrador.dto.TurnoDto;
import com.example.integrador.entity.Turno;
import com.example.integrador.exceptions.ResourceNotFoundException;
import com.example.integrador.utils.JsonPrinter;
import com.example.integrador.exceptions.BadRequestException;
import com.example.integrador.repository.TurnoRepository;
import com.example.integrador.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoDto registrarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoDto = null;
        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if (paciente == null || odontologo == null){
            if (paciente == null && odontologo == null){
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra eb la base de datos");
            } else if (paciente == null) {
                LOGGER.error("El pacuente no se encuentra en la base de datos");
                throw new BadRequestException("El paciente no se encuentra en la base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
                throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
            }
        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("Nuevo turno registrado con exito: {}", JsonPrinter.toString(turnoDto));
        }
        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnoDtoList = turnos.stream().map(TurnoDto::fromTurno).toList();
        LOGGER.info("Lista de todos los turnos: {}", JsonPrinter.toString(turnoDtoList));
        return turnoDtoList;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;
        if (turnoBuscado != null){
            turnoDto = TurnoDto.fromTurno(turnoBuscado);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoDto));
        } else {
            LOGGER.info("El id no se encuentra registrado en la base de datos");
        }
        return turnoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        Turno turnoActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoDtoActualizado = null;
        if (turnoActualizar != null){
            turnoActualizar = turno;
            turnoRepository.save(turnoActualizar);
            turnoDtoActualizado = TurnoDto.fromTurno(turnoActualizar);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoDtoActualizado));
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el turno no se encuentra registrado");
        }
        return turnoDtoActualizado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id " + id);
        }
    }
}
