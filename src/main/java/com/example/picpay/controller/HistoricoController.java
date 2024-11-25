package com.example.picpay.controller;

import com.example.picpay.model.Transacao;
import com.example.picpay.repository.TransacaoRepository;
import com.example.picpay.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<ApiResponse<List<Transacao>>> obterHistorico(@PathVariable Long idUsuario) {
        try {
            // Validação simples do ID do usuário
            if (idUsuario == null || idUsuario <= 0) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(400, "ID de usuário inválido.", null));
            }

            List<Transacao> historico = transacaoRepository.findByIdRemetenteOrIdDestinatario(idUsuario, idUsuario);

            if (historico.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Nenhum histórico encontrado para o usuário.", null));
            }

            return ResponseEntity.ok(new ApiResponse<>(200, "Histórico recuperado com sucesso.", historico));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, "Parâmetros inválidos: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Erro ao recuperar o histórico. Detalhes: " + e.getMessage(), null));
        }
    }
}
