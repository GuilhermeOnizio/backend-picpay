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
            if (usuario == null || usuario.getNome() == null || usuario.getNome().isEmpty()) {
                return ResponseEntity.badRequest().body("Erro de validação: Nome do usuário é obrigatório.");
            }

            Usuario novoUsuario = usuarioService.criarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o usuário. Tente novamente mais tarde.");
        }
    }
}
