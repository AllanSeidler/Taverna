package com.taverna.repository;

import com.taverna.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {}
