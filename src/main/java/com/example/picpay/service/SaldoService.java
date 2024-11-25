package com.example.picpay.service;

import com.example.picpay.model.Usuario;
import com.example.picpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SaldoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Adiciona saldo
    public Usuario adicionarSaldo(Long usuarioId, BigDecimal valor) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setSaldo(usuario.getSaldo().add(valor));
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Usuário não encontrado");
    }

    //Consulta o saldo
    public BigDecimal consultarSaldo(Long usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().getSaldo();
        }
        throw new RuntimeException("Usuário não encontrado");
    }
}
