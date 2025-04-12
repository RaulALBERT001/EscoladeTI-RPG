package com.gerenciamentos.produtos2.controllers;

import com.gerenciamentos.produtos2.entities.ItemMagico;
import com.gerenciamentos.produtos2.entities.Personagem;
import com.gerenciamentos.produtos2.services.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personagens")
@Tag(name = "Personagens", description = "API para gerenciamento de personagens e seus itens mágicos")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @Operation(summary = "Criar um novo personagem", description = "Cria um novo personagem com os atributos especificados")
    @PostMapping
    public ResponseEntity<Personagem> criarPersonagem(@RequestBody Personagem personagem) {
        return ResponseEntity.ok(personagemService.criarPersonagem(personagem));
    }

    @Operation(summary = "Listar todos os personagens", description = "Retorna uma lista com todos os personagens cadastrados")
    @GetMapping
    public ResponseEntity<List<Personagem>> listarPersonagens() {
        return ResponseEntity.ok(personagemService.listarPersonagens());
    }

    @Operation(summary = "Buscar um personagem por ID", description = "Retorna um personagem específico baseado no ID fornecido")
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPersonagemPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.buscarPersonagemPorId(id));
    }

    @Operation(summary = "Atualizar nome do aventureiro", description = "Atualiza o nome de um personagem específico")
    @PatchMapping("/{id}/nome-aventureiro")
    public ResponseEntity<Personagem> atualizarNomeAventureiro(
            @PathVariable Long id,
            @RequestBody String novoNome) {
        return ResponseEntity.ok(personagemService.atualizarNomeAventureiro(id, novoNome));
    }

    @Operation(summary = "Remover um personagem", description = "Remove um personagem específico baseado no ID fornecido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPersonagem(@PathVariable Long id) {
        personagemService.removerPersonagem(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Adicionar item mágico ao personagem", description = "Adiciona um item mágico específico ao inventário do personagem")
    @PostMapping("/{personagemId}/itens/{itemId}")
    public ResponseEntity<Personagem> adicionarItemMagico(
            @PathVariable Long personagemId,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(personagemService.adicionarItemMagico(personagemId, itemId));
    }

    @Operation(summary = "Listar itens mágicos do personagem", description = "Retorna todos os itens mágicos que um personagem possui")
    @GetMapping("/{personagemId}/itens")
    public ResponseEntity<List<ItemMagico>> listarItensMagicosPorPersonagem(
            @PathVariable Long personagemId) {
        return ResponseEntity.ok(personagemService.listarItensMagicosPorPersonagem(personagemId));
    }

    @Operation(summary = "Remover item mágico do personagem", description = "Remove um item mágico específico do inventário do personagem")
    @DeleteMapping("/{personagemId}/itens/{itemId}")
    public ResponseEntity<Void> removerItemMagico(
            @PathVariable Long personagemId,
            @PathVariable Long itemId) {
        personagemService.removerItemMagico(personagemId, itemId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar amuleto do personagem", description = "Retorna o amuleto equipado pelo personagem, se houver")
    @GetMapping("/{personagemId}/amuleto")
    public ResponseEntity<ItemMagico> buscarAmuletoPorPersonagem(
            @PathVariable Long personagemId) {
        ItemMagico amuleto = personagemService.buscarAmuletoPorPersonagem(personagemId);
        return amuleto != null ? ResponseEntity.ok(amuleto) : ResponseEntity.notFound().build();
    }
}