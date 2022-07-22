package com.idat.reservacita.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioClienteReq {
    private String username;
    private String password;
    private String role;
    private ClienteUCreq cliente;
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ClienteUCreq{
    private String nombre;
    private String celular;
}