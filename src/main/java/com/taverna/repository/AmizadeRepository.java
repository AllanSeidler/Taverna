package com.taverna.repository;

import com.taverna.model.Amizade;
import com.taverna.model.AmizadeID;
import com.taverna.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AmizadeRepository extends JpaRepository<Amizade, AmizadeID> {

    @Query("SELECT u FROM Usuario u join Amizade a on " +
            "((a.amizadeID.usuario1Id = :friendId and a.amizadeID.usuario2Id = :userId) or" +
            "(a.amizadeID.usuario2Id = :friendId and a.amizadeID.usuario1Id = :userId)) " +
            "and u.id = :friendId and a.aceita=true")
    Usuario findFriendById(@Param("friendId") int friendId, @Param("userId") int userId);
}
