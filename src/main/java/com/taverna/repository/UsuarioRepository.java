package com.taverna.repository;

import com.taverna.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * @author AllanSeidler
 * */
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.login = :login and u.senha = :senha")
    Usuario findUserByLogin(@Param("login") String login,
                                        @Param("senha") String senha);


}
