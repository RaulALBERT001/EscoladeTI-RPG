package com.gerenciamentos.produtos2.entities;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "personagens")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)
    private String nome;


    @Column(name = "nome_aventureiro", nullable = false)
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private ClassePersonagem classe;


    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Integer forca;

    @Column(nullable = false)
    private Integer defesa;

    @OneToMany(mappedBy = "personagem", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference//como tive problemas com a serialization do json(Recursão inifinita )
    //tive que adicionar o JsonBackReference para que a mesma não acontecesse
    //o JsonManagedReference é usado para indicar que a referência deve ser serializada
    private List<ItemMagico> itensMagicos = new ArrayList<>();


    
    public Personagem() {}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    public ClassePersonagem getClasse() {
        return classe;
    }


    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

    public Integer getForcaTotal() {
        return forca + (itensMagicos != null ? itensMagicos.stream()
                .mapToInt(ItemMagico::getForca)
                .sum() : 0);
    }

    public Integer getDefesaTotal() {
        return defesa + (itensMagicos != null ? itensMagicos.stream()
                .mapToInt(ItemMagico::getDefesa)
                .sum() : 0);
    }

    public void adicionarItemMagico(ItemMagico item) {
        if (itensMagicos == null) {
            itensMagicos = new ArrayList<>();
        }
        //adiciona o item À lista de itens
        itensMagicos.add(item);

        if (item != null) {
            item.setPersonagem(this);
        }
    }

    public void removerItemMagico(ItemMagico item) {
        if (itensMagicos != null && item != null) {
            itensMagicos.remove(item);
            item.setPersonagem(null);
        }
    }

    
}