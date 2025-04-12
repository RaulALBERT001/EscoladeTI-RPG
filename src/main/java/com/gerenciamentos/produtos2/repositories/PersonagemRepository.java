package com.gerenciamentos.produtos2.repositories;

import com.gerenciamentos.produtos2.entities.Personagem;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    
    @Query("SELECT p FROM Personagem p LEFT JOIN FETCH p.itensMagicos WHERE p.id = :id")
    Optional<Personagem> findByIdWithItensMagicos(@Param("id") Long id);
}