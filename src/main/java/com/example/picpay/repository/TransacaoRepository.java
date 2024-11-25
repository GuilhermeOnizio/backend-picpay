package com.example.picpay.repository;

import com.example.picpay.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByIdRemetenteOrIdDestinatario(Long idRemetente, Long idDestinatario );
}
