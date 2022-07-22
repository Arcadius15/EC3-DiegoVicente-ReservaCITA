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
public class ClienteRes {
    private int idCliente;
    private String nombre;
    private String celular;

    private Set<HospitalCliRes> hospitalClientes;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class HospitalCliRes{
    private int idHospital;
}
