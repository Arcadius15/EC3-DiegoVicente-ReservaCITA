package com.idat.reservacita.dto;

import org.mapstruct.Mapper;

import com.idat.reservacita.model.Cliente;
import com.idat.reservacita.model.Hospital;
import com.idat.reservacita.model.UsuarioCliente;

@Mapper(componentModel = "spring")
public interface MapperDTO {
    //Cliente DTO's
    ClienteRes clienteToDto(Cliente item);

    //Hospital DTO's
    HospitalRes hospitalToDto(Hospital item);
    Hospital dtoToHospital(HospitalReq item);

    //UsuarioCliente DTO's
    UsuarioCliente dtoToUsuarioCliente(UsuarioClienteReq item);
    UsuarioClienteRes usuarioClienteToDto(UsuarioCliente item);

}
