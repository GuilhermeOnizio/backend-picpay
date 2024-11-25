package com.example.picpay.controller;

import com.example.picpay.model.Transacao;
import com.example.picpay.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/{idUsuario}")
    public List<Transacao> obterHistorico(@PathVariable Long idUsuario) {
        return transacaoRepository.findByIdRemetenteOrIdDestinatario(idUsuario, idUsuario);
    }
}
