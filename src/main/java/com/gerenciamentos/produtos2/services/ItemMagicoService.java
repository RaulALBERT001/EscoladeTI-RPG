package com.gerenciamentos.produtos2.services;

import com.gerenciamentos.produtos2.entities.ItemMagico;
import com.gerenciamentos.produtos2.repositories.ItemMagicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Transactional
    public ItemMagico criarItemMagico(ItemMagico item) {
        validarAtributos(item);
        ajustarAtributosPorTipo(item);
        return itemMagicoRepository.save(item);
    }

    private void validarAtributos(ItemMagico item) {
        
        if (item.getForca() < 0 || item.getDefesa() < 0) {
            throw new IllegalArgumentException("Força e Defesa não podem ser negativos :)");
        }

        if (item.getForca() > 10 || item.getDefesa() > 10) {
            throw new IllegalArgumentException("Força e Defesa não podem exceder 10 :(");
        }

        if (item.getForca() == 0 && item.getDefesa() == 0) {
            throw new IllegalArgumentException("O item deve ter pelo menos Força ou Defesa maior que zero :()");
        }
    }

    private void ajustarAtributosPorTipo(ItemMagico item) {
        switch (item.getTipoItem()) {
            case ARMA:
                item.setDefesa(0);
                break;
            case ARMADURA:
                item.setForca(0);
                break;
            case AMULETO:
                // Amuleto pode ter ambos os atributos, por isso nenhuma alteração é necessária
                break;
        }
    }

    @Transactional(readOnly = true)
    public List<ItemMagico> listarItensMagicos() {
       
        return itemMagicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ItemMagico buscarItemMagicoPorId(Long id) {
        
        return itemMagicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item Mágico não encontrado:("));
    }

    @Transactional
    public void removerItemMagico(Long id) {
        if (!itemMagicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Item Mágico não encontrado :)");
        }
        itemMagicoRepository.deleteById(id);
    }
}