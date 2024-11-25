package com.example.picpay.controller;

import com.example.picpay.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    // Endpoint para realizar a transferência
    @PostMapping("/realizar")
    public String transferir(@RequestParam Long usuarioRemetenteId,
                             @RequestParam Long usuarioDestinatarioId,
                             @RequestParam BigDecimal valor) {
        return transferenciaService.transferir(usuarioRemetenteId, usuarioDestinatarioId, valor);
    }
}
