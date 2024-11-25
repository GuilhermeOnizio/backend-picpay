package com.example.picpay.controller;

import com.example.picpay.service.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/saldo")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    // Endpoint para adicionar saldo
    @PostMapping("/adicionar/{usuarioId}")
    public ResponseEntity<String> adicionarSaldo(@PathVariable Long usuarioId, @RequestBody BigDecimal valor) {
        try {
            if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body("O valor deve ser maior que zero.");
            }

            saldoService.adicionarSaldo(usuarioId, valor);
            return ResponseEntity.ok("Saldo adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar saldo. Tente novamente mais tarde.");
        }
    }

    // Endpoint para consultar saldo
    @GetMapping("/consultar/{usuarioId}")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable Long usuarioId) {
        try {
            BigDecimal saldo = saldoService.consultarSaldo(usuarioId);
            if (saldo == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 se o saldo não for encontrado
            }
            return ResponseEntity.ok(saldo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 para outros erros
        }
    }
}
