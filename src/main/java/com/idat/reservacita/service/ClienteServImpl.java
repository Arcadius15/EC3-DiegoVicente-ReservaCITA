package com.idat.reservacita.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.reservacita.model.Cliente;
import com.idat.reservacita.repository.ClienteRepository;

@Service
public class ClienteServImpl implements ClienteServ{

    @Autowired
    private ClienteRepository repository;

    @Override
    public void guardar(Cliente item) {
        repository.save(item);
    }

    @Override
    public void editar(Cliente item) {
        repository.saveAndFlush(item);
        
    }

    @Override
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    public Cliente buscar(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }
    
}
