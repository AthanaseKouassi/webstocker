/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Athanase
 */
@Entity
@SuppressWarnings ("PersistenceUnitPresent")
public class LigneBonDeSortie implements Serializable{
    
    @Embeddable
    public static class Id implements Serializable {
        // composantes de la clé composite
        // pointe sur une Article
        
        @Column(name = "id_article")
        private int idArticle;
        
        @Column(name = "id_bs")
        private int idBonSortie;

        public Id() {
        }

        public Id(int idArticle, int idBonSortie) {
            this.idArticle = idArticle;
            this.idBonSortie = idBonSortie;
        }       

        public int getIdArticle() {
            return idArticle;
        }

        public void setIdArticle(int idArticle) {
            this.idArticle = idArticle;
        }

        public int getIdBonSortie() {
            return idBonSortie;
        }

        public void setIdBonSortie(int idBonSortie) {
            this.idBonSortie = idBonSortie;
        }
            
    }
    
    @EmbeddedId
    private Id id = new Id();
    
    @Column(name ="qte_bs")
    private int quantiteBonSortie;
    
    // relation principale LigneBonDeSortie (many) -> BonDeSortie (one)
    // implémentée par la clé étrangère : idBonSortie (LigneBonDeSortie (many) -> BonDeSortie (one)
    // idArticle est en même temps élément de la clé primaire composite
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur
    @ManyToOne()
    @JoinColumn(name = "id_bs", insertable = false, updatable = false)
    private BonDeSortie bondesortie;
    
    // relation principale LigneBonDeSortie (many) -> Article (one)
    // implémentée par la clé étrangère : idArticle (LigneBonDeSortie (many) -> Article (one)
    // idArticle est en même temps élément de la clé primaire composite
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur
    @ManyToOne()
    @JoinColumn(name = "id_article", insertable = false, updatable = false)
    private Article article;

    public LigneBonDeSortie() {
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public int getQuantiteBonSortie() {
        return quantiteBonSortie;
    }

    public void setQuantiteBonSortie(int quantiteBonSortie) {
        this.quantiteBonSortie = quantiteBonSortie;
    }

    public BonDeSortie getBonDeSortie() {
        return bondesortie;
    }

    public void setBonDeSortie(BonDeSortie bondesortie) {
        this.bondesortie = bondesortie;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

   
}
