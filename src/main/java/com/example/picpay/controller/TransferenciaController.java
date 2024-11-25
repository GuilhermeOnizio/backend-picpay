package com.example.picpay.controller;

import com.example.picpay.service.TransferenciaService;
import com.example.picpay.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    // Endpoint para realizar a transferência
    @PostMapping("/realizar")
    public ResponseEntity<ApiResponse<String>> transferir(
            @RequestParam Long usuarioRemetenteId,
            @RequestParam Long usuarioDestinatarioId,
            @RequestParam BigDecimal valor) {
        try {
            // Validações de entrada
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(400, "O valor da transferência deve ser maior que zero.", null));
            }

            // Executa a transferência
            String resultado = transferenciaService.transferir(usuarioRemetenteId, usuarioDestinatarioId, valor);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, "Transferência realizada com sucesso.", resultado));

        } catch (IllegalArgumentException e) {
            // Exceção lançada por argumentos inválidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, e.getMessage(), null));

        } catch (Exception e) {
            // Tratamento para erros inesperados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Erro ao processar a transferência.", null));
        }
    }
}
