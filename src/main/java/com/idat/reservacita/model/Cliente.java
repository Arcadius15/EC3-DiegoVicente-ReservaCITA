package com.idat.reservacita.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    private String nombre;
    private String celular;

    @OneToOne
    private UsuarioCliente usuarioCliente;

    @ManyToMany
    @JoinTable(name = "hospital_clientes",joinColumns = @JoinColumn(name="id_cliente")
        ,inverseJoinColumns = @JoinColumn(name="id_hospital"))
    private Set<Hospital> hospitalClientes;
}
