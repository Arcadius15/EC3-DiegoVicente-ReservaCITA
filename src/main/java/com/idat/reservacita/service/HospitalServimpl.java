package com.idat.reservacita.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.reservacita.model.Hospital;
import com.idat.reservacita.repository.HospitalRepository;

@Service
public class HospitalServimpl implements HospitalServ{

    @Autowired
    private HospitalRepository repository;

    @Override
    public void guardar(Hospital item) {
        repository.save(item);
    }

    @Override
    public void editar(Hospital item) {
        repository.saveAndFlush(item);
    }

    @Override
    public void eliminar(int id) {
        repository.deleteById(id);
    }

    @Override
    public Hospital buscar(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Hospital> listar() {
        return repository.findAll();
    }
    
}
