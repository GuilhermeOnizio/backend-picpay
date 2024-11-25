package com.example.picpay.controller;

import com.example.picpay.service.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/saldo")
public class SaldoController {
    @Autowired
    private SaldoService saldoService;

    // Endpoint para adicionar saldo
    @PostMapping("/adicionar/{usuarioId}")
    public String adicionarSaldo(@PathVariable Long usuarioId, @RequestBody BigDecimal valor) {
        saldoService.adicionarSaldo(usuarioId, valor);
        return "Saldo adicionado com sucesso!";
    }

    // Endpoint para consultar saldo
    @GetMapping("/consultar/{usuarioId}")
    public BigDecimal consultarSaldo(@PathVariable Long usuarioId) {
        return saldoService.consultarSaldo(usuarioId);
    }
}
