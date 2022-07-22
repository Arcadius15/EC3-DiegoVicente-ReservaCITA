package com.idat.reservacita.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRes {
    private int idHospital;
    private String nombre;
    private String descripcion;
    private String distrito;
    private Set<ClienteHres> clientes;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ClienteHres{
    private int idCliente;
}