package com.gerenciamentos.produtos2.repositories;
import com.gerenciamentos.produtos2.entities.ItemMagico;
import com.gerenciamentos.produtos2.entities.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long> {
    
    List<ItemMagico> findByPersonagemId(Long personagemId);
    
    @Query("SELECT i FROM ItemMagico i WHERE i.personagem.id = :personagemId AND i.tipoItem = :tipoItem")
    Optional<ItemMagico> findByPersonagemIdAndTipoItem(@Param("personagemId") Long personagemId, @Param("tipoItem") TipoItem tipoItem);
}