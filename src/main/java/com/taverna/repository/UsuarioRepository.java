package com.taverna.repository;

import com.taverna.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author AllanSeidler
 * */
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {}
