package com.example.integrador.service.impl;

import com.example.integrador.dto.OdontologoDto;
import com.example.integrador.entity.Odontologo;
import com.example.integrador.repository.OdontologoRepository;
import com.example.integrador.service.IOdontologoService;
import com.example.integrador.utils.JsonPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final ObjectMapper objectMapper;
    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(ObjectMapper objectMapper, OdontologoRepository odontologoRepository) {
        this.objectMapper = objectMapper;
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findBy(id).orElse(null);
        OdontologoDto odontologoDto = null;
        if (odontologoBuscado != null){
            odontologoDto = objectMapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoDto));
        } else {
            LOGGER.info("El id no se encuentra registrado en la base de datos");
        }
        return odontologoDto;
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
