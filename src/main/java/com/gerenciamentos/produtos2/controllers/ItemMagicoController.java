package com.gerenciamentos.produtos2.controllers;

import com.gerenciamentos.produtos2.entities.ItemMagico;
import com.gerenciamentos.produtos2.services.ItemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-magicos")
@Tag(name = "Itens Mágicos", description = "API para gerenciamento de itens mágicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @Operation(summary = "Criar um novo item mágico", description = "Cria um novo item mágico com os atributos especificados(crie o item sem personagem)")
   

    @PostMapping
    public ResponseEntity<ItemMagico> criarItemMagico(@RequestBody ItemMagico item) {
        return ResponseEntity.ok(itemMagicoService.criarItemMagico(item));
    }

    @Operation(summary = "Listar todos os itens mágicos", description = "Retorna uma lista com todos os itens mágicos cadastrados")
   
    @GetMapping
    public ResponseEntity<List<ItemMagico>> listarItensMagicos() {
        return ResponseEntity.ok(itemMagicoService.listarItensMagicos());
    }

    @Operation(summary = "Buscar um item mágico por ID", description = "Retorna um item mágico específico baseado no ID fornecido")
    

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> buscarItemMagicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemMagicoService.buscarItemMagicoPorId(id));
    }

    @Operation(summary = "Remover um item mágico", description = "Remove um item mágico específico baseado no ID fornecido")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItemMagico(@PathVariable Long id) {
        itemMagicoService.removerItemMagico(id);
        return ResponseEntity.noContent().build();
    }
}