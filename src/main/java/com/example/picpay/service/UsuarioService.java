package com.example.picpay.service;

import com.example.picpay.model.Usuario;
import com.example.picpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSaldo(BigDecimal.ZERO);
        return usuarioRepository.save(usuario);
    }

}
