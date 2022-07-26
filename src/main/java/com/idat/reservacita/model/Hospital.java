package com.idat.reservacita.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHospital;
    private String nombre;
    private String descripcion;
    private String distrito;

    @ManyToMany(mappedBy = "hospitalClientes")
    private Set<Cliente> clientes;
}
