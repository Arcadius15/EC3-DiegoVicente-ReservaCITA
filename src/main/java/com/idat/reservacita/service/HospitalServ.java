package com.idat.reservacita.service;

import java.util.List;

import com.idat.reservacita.model.Hospital;

public interface HospitalServ {
    public void guardar(Hospital item);
    public void editar(Hospital item);
    public void eliminar(int id);
    public Hospital buscar(int id);
    public List<Hospital> listar();
}
