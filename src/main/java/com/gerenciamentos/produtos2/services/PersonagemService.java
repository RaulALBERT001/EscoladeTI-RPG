package com.gerenciamentos.produtos2.services;

import com.gerenciamentos.produtos2.entities.ItemMagico;
import com.gerenciamentos.produtos2.entities.Personagem;
import com.gerenciamentos.produtos2.entities.TipoItem;
import com.gerenciamentos.produtos2.repositories.ItemMagicoRepository;
import com.gerenciamentos.produtos2.repositories.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Transactional
    public Personagem criarPersonagem(Personagem personagem) {
        validarDistribuicaoPontos(personagem);
        return personagemRepository.save(personagem);
    }

    private void validarDistribuicaoPontos(Personagem personagem) {
        int totalPontos = personagem.getForca() + personagem.getDefesa();
        if (totalPontos > 10) {
            throw new IllegalArgumentException("O total de pontos distribuídos entre Força e Defesa não pode exceder 10");
        }
    }

    @Transactional(readOnly = true)
    public List<Personagem> listarPersonagens() {
        return personagemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Personagem buscarPersonagemPorId(Long id) {
        return personagemRepository.findByIdWithItensMagicos(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado:/"));
    }

    @Transactional
    public Personagem atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem personagem = buscarPersonagemPorId(id);
        personagem.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagem);
    }

    @Transactional
    public void removerPersonagem(Long id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado:/"));
        
        // limpa todos os itens magicos que o personagem possuia :)
        personagem.getItensMagicos().clear();
        personagemRepository.save(personagem);
        
        try {
            personagemRepository.delete(personagem);
            personagemRepository.flush(); // força o flush para garantir que a remoção seja feita
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover personagem: " + e.getMessage());
        }
    }

    @Transactional
    public Personagem adicionarItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = buscarPersonagemPorId(personagemId);


        ItemMagico item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item Mágico não encontrado:/"));

                
        if (item.getTipoItem() == TipoItem.AMULETO) {
            validarAmuleto(personagem);
        }

        personagem.adicionarItemMagico(item);
        return personagemRepository.save(personagem);
    }

    private void validarAmuleto(Personagem personagem) {
        boolean possuiAmuleto = personagem.getItensMagicos().stream()
                .anyMatch(item -> item.getTipoItem() == TipoItem.AMULETO);
        if (possuiAmuleto) {
            throw new IllegalStateException("O personagem já possui um amuleto :/");
        }
    }

    @Transactional
    public void removerItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = buscarPersonagemPorId(personagemId);
        ItemMagico item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item Mágico não encontrado:/"));

        personagem.removerItemMagico(item);
        personagemRepository.save(personagem);
    }

    @Transactional(readOnly = true)
    public List<ItemMagico> listarItensMagicosPorPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemId(personagemId)    ;
    }

    @Transactional(readOnly = true)
    public ItemMagico buscarAmuletoPorPersonagem(Long personagemId) {
        return itemMagicoRepository.findByPersonagemIdAndTipoItem(personagemId, TipoItem.AMULETO)
                .orElse(null);
    }
}