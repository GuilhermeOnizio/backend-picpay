package com.example.picpay.controller;

import com.example.picpay.model.Usuario;
import com.example.picpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
        try {
            // Verificação de campos obrigatórios
            if (usuario == null) {
                return ResponseEntity.badRequest().body("Erro de validação: Dados do usuário são obrigatórios.");
            }

            if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
                return ResponseEntity.badRequest().body("Erro de validação: Nome completo é obrigatório.");
            }

            if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
                return ResponseEntity.badRequest().body("Erro de validação: Senha é obrigatória.");
            }

            if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("Erro de validação: Email é obrigatório.");
            }

            if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
                return ResponseEntity.badRequest().body("Erro de validação: CPF é obrigatório.");
            }

            // Tenta criar o usuário
            Usuario novoUsuario = usuarioService.criarUsuario(usuario);

            // Se não houver exceções, retorna o usuário criado com status 201 (CREATED)
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);

        } catch (IllegalArgumentException e) {
            // Captura o erro de duplicidade de email ou CPF e retorna mensagem adequada
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());

        } catch (Exception e) {
            // Captura qualquer outro erro não esperado e retorna a mensagem padrão
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o usuário. Tente novamente mais tarde.");
        }
    }
}
