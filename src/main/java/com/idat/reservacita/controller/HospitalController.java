package com.idat.reservacita.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.reservacita.dto.HospitalCliente;
import com.idat.reservacita.dto.HospitalReq;
import com.idat.reservacita.dto.HospitalRes;
import com.idat.reservacita.dto.MapperDTO;
import com.idat.reservacita.model.Cliente;
import com.idat.reservacita.model.Hospital;
import com.idat.reservacita.service.ClienteServ;
import com.idat.reservacita.service.HospitalServ;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospitalServ serv;

    @Autowired
    private ClienteServ cliserv;

    @Autowired
    private MapperDTO mapper;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<HospitalRes> lista = serv.listar().stream().map(new Function<Hospital,HospitalRes>() {
            @Override
            public HospitalRes apply(Hospital t) {
                return mapper.hospitalToDto(t);
            }
        }).collect(Collectors.toList());
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable int id){
        HospitalRes res = mapper.hospitalToDto(serv.buscar(id));
        if (res!=null) {
            return new ResponseEntity<>(res,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody HospitalReq item){
        try {
           serv.guardar(mapper.dtoToHospital(item)); 
           return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Map<Object,Object> item) {
        Hospital existingItem = serv.buscar(id);
        if (existingItem!=null) {
        	item.forEach((key,value)->{
                if (((String)key).contains("id")) return; 
        		Field field = ReflectionUtils.findField(Hospital.class, (String) key);
                field.setAccessible(true);
				ReflectionUtils.setField(field, existingItem, value);
        	});
            serv.editar(existingItem);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            serv.eliminar(id);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcliente")
    public ResponseEntity<?> addCliente(@RequestBody HospitalCliente ids){
        Hospital h = serv.buscar(ids.getIdHospital());
        Cliente c = cliserv.buscar(ids.getIdCliente());
        if (h!=null&&c!=null) {
            var clientes = h.getClientes();
            Boolean nexists = clientes.stream().noneMatch(x->x.equals(c));
            if (nexists) {
                clientes.add(c);
                h.setClientes(clientes);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

}
