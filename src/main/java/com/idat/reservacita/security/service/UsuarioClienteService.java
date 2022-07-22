package com.idat.reservacita.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idat.reservacita.model.UsuarioCliente;
import com.idat.reservacita.repository.UsuarioClienteRepository;

@Service
public class UsuarioClienteService implements UserDetailsService{

    @Autowired
    private UsuarioClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioCliente user = repository.findByUsername(username).orElse(null);
        if (user!=null) {
            return user;
        }else{
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }

    @Transactional
    public UsuarioCliente save(UsuarioCliente item){
        item.getCliente().setUsuarioCliente(item);
        return repository.save(item);
    }

    @Transactional
    public void edit(UsuarioCliente item){
        repository.save(item);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UsuarioCliente get(int id){
        return repository.findById(id).orElse(null);
    }
    
}
