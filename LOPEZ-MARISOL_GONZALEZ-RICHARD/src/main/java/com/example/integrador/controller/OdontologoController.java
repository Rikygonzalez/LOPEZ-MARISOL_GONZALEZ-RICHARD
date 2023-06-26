package com.example.integrador.controller;

import com.example.integrador.dto.OdontologoDto;
import com.example.integrador.entity.Odontologo;
import com.example.integrador.exceptions.ResourceNotFoundException;
import com.example.integrador.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //GET
    @GetMapping()
    public List<OdontologoDto> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarOdontologoPorId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), null, HttpStatus.OK);
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registrarOdonotologo(@RequestBody Odontologo odontologo){
        return new ResponseEntity<>(odontologoService.registrarOdonotologo(odontologo), null, HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@RequestBody Odontologo odontologo){
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologo), null, HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odotnologo eliminado");
    }
}