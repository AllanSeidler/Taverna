package com.taverna.repository;

import com.taverna.model.Mensagem;
import com.taverna.model.MensagemID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.beans.Transient;
import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, MensagemID> {

    @Query("SELECT m FROM Mensagem m WHERE " +
            "(m.mensagemID.remetente = :remetenteId and m.mensagemID.destinatario= :destinatarioId) or " +
            "(m.mensagemID.destinatario = :remetenteId and m.mensagemID.remetente= :destinatarioId) " +
            "ORDER BY m.mensagemID.data")
    List<Mensagem> findMensagensByUsers(@Param("remetenteId") int remetenteId, @Param("destinatarioId") int destinatarioId);




//    @Query(value = "INSERT INTO Mensagem " +
//            "VALUES (:remetenteId,:destinatarioId,:conteudo,NOW())", nativeQuery = true)
//    void addMensagem(@Param("remetenteId") int remetenteId, @Param("destinatarioId") int destinatarioId,
//                     @Param("conteudo") String conteudo);
}
