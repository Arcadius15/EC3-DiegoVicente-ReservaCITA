package com.idat.reservacita.service;

import java.util.List;

import com.idat.reservacita.model.Cliente;

public interface ClienteServ {
    public void guardar(Cliente item);
    public void editar(Cliente item);
    public void eliminar(int id);
    public Cliente buscar(int id);
    public List<Cliente> listar();
}
