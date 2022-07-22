package com.idat.reservacita.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.reservacita.dto.MapperDTO;
import com.idat.reservacita.dto.UsuarioClienteReq;
import com.idat.reservacita.dto.UsuarioClienteRes;
import com.idat.reservacita.model.UsuarioCliente;
import com.idat.reservacita.security.service.UsuarioClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuario")
public class UsuarioClienteController {


    @Autowired
    private UsuarioClienteService service;

    @Autowired
    private MapperDTO mapper;

    @GetMapping
    public ResponseEntity<?> get(@RequestBody int id) {
        UsuarioClienteRes res = mapper.usuarioClienteToDto(service.get(id));
        if (res!=null) {
            return new ResponseEntity<>(res,HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 

    @PostMapping("registrar")
    public ResponseEntity<?> create(@RequestBody UsuarioClienteReq item) {
        UsuarioCliente uc = mapper.dtoToUsuarioCliente(item);
        try {
            UsuarioClienteRes res = mapper.usuarioClienteToDto(service.save(uc)) ;
            return new ResponseEntity<>(res,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
