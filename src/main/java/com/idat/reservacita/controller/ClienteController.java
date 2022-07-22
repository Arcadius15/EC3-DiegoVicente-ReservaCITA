package com.idat.reservacita.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.reservacita.dto.ClienteRes;
import com.idat.reservacita.dto.MapperDTO;
import com.idat.reservacita.model.Cliente;
import com.idat.reservacita.service.ClienteServ;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServ serv;

    @Autowired
    private MapperDTO mapper;

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        try {
            ClienteRes item = mapper.clienteToDto(serv.buscar(id));
            if (item!=null) {
                return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteRes>> getAll() {
        List<ClienteRes> lista = serv.listar().stream().map(new Function<Cliente,ClienteRes>() {
            @Override
            public ClienteRes apply(Cliente t) {
                return mapper.clienteToDto(t);
            }
        }).collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Map<Object,Object> item) {
        Cliente existingItem = serv.buscar(id);
        if (existingItem!=null) {
        	item.forEach((key,value)->{
                if (((String)key).contains("id")) return; 
        		Field field = ReflectionUtils.findField(Cliente.class, (String) key);
                field.setAccessible(true);
				ReflectionUtils.setField(field, existingItem, value);
        	});
            serv.editar(existingItem);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
