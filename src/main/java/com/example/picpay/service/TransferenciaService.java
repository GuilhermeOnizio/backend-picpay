package com.example.picpay.service;

import com.example.picpay.model.Transacao;
import com.example.picpay.model.Usuario;
import com.example.picpay.repository.TransacaoRepository;
import com.example.picpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    // Transferência de Saldo
    public String transferir(Long usuarioRemetenteId, Long usuarioDestinatarioId, BigDecimal valor) {
        Optional<Usuario> remetenteOpt = usuarioRepository.findById(usuarioRemetenteId);
        Optional<Usuario> destinatarioOpt = usuarioRepository.findById(usuarioDestinatarioId);

        if (remetenteOpt.isPresent() && destinatarioOpt.isPresent()) {
            Usuario remetente = remetenteOpt.get();
            Usuario destinatario = destinatarioOpt.get();

            // Verifica se o remetente tem saldo suficiente
            if (remetente.getSaldo().compareTo(valor) < 0) {
                throw new RuntimeException("Saldo insuficiente para realizar a transferência");
            }

            // Realiza a transferência
            remetente.setSaldo(remetente.getSaldo().subtract(valor));
            destinatario.setSaldo(destinatario.getSaldo().add(valor));

            // Salva as alterações
            usuarioRepository.save(remetente);
            usuarioRepository.save(destinatario);

            // Registra a transação
            Transacao transacao = new Transacao();
            transacao.setIdRemetente(usuarioRemetenteId);
            transacao.setIdDestinatario(usuarioDestinatarioId);
            transacao.setValor(valor);

            transacaoRepository.save(transacao);

            return "Transferência realizada com sucesso!";
        } else {
            throw new RuntimeException("Um ou ambos os usuários não foram encontrados.");
        }
    }
}
