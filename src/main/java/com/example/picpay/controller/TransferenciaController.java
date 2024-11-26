package com.example.picpay.controller;

import com.example.picpay.service.TransferenciaService;
import com.example.picpay.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    // Endpoint para realizar a transferência
    @PostMapping("/realizar")
    public ResponseEntity<ApiResponse<String>> transferir(@RequestBody Map<String, Object> transferenciaDados) {
        try {
            // Extrai os dados do corpo da requisição
            Long usuarioRemetenteId = Long.valueOf(transferenciaDados.get("idRemetente").toString());
            Long usuarioDestinatarioId = Long.valueOf(transferenciaDados.get("idDestinatario").toString());
            BigDecimal valor = new BigDecimal(transferenciaDados.get("valor").toString());

            // Validações de entrada
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(400, "O valor da transferência deve ser maior que zero.", null));
            }

            // Executa a transferência
            transferenciaService.transferir(usuarioRemetenteId, usuarioDestinatarioId, valor);

            // Formata a data e hora da transação
            String dataTransacao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, "Transferência realizada com sucesso.", "Data da transação: " + dataTransacao));

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
