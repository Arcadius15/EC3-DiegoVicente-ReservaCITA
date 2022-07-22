package com.idat.reservacita.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioClienteRes {
    private int idUsuario;
    private String username;
    private String role;
    private ClienteUCres cliente;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ClienteUCres{
    private int idCliente;
    private String nombre;
    private String celular;
}