package com.gerenciamentos.RPG.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String nomeAventureiro;
    @Enumerated(EnumType.STRING)
    private ClassesEnum classes;

    private int level;

    private List<MagicItem> listaItensMagicos;
    private int forca;
    private int defesa;

    private int pontos;



    public Personagem(int id, String nome, String nomeAventureiro, ClassesEnum classes, int level, List<MagicItem> listaItensMagicos, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classes = classes;
        this.level = level;
        this.listaItensMagicos = listaItensMagicos;
        this.forca = forca;
        this.defesa = defesa;
        this.pontos = 10;
    }

    public Personagem(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public ClassesEnum getClasses() {
        return classes;
    }

    public void setClasses(ClassesEnum classes) {
        this.classes = classes;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<MagicItem> getListaItensMagicos() {
        return listaItensMagicos;
    }

    public void setListaItensMagicos(List<MagicItem> listaItensMagicos) {
        this.listaItensMagicos = listaItensMagicos;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
