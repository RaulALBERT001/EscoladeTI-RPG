package com.gerenciamentos.produtos2.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "itens_magicos")
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_item", nullable = false)
    private TipoItem tipoItem;

    @Column(nullable = false)
    private Integer forca;

    @Column(nullable = false)
    private Integer defesa;

    @ManyToOne(optional = true)
    @JoinColumn(name = "personagem_id", nullable = true)
    @JsonBackReference//para evitar a recursão infinita
    private Personagem personagem;

    public ItemMagico() {}

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

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
        if (tipoItem == TipoItem.ARMA) {
            this.defesa = 0;
        } else if (tipoItem == TipoItem.ARMADURA) {
            this.forca = 0;
        }
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        if (this.tipoItem == TipoItem.ARMADURA) {
            this.forca = 0;
        } else {
            this.forca = Math.min(forca, 10);
        }
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        if (this.tipoItem == TipoItem.ARMA) {
            this.defesa = 0;
        } else {
            this.defesa = Math.min(defesa, 10);
        }
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}