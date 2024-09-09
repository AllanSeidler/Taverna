package com.taverna.repository;

import com.taverna.model.Mensagem;
import com.taverna.model.MensagemID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, MensagemID> {
}
