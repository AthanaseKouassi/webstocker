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
public class LigneApprovisionnement  implements Serializable{
    
    @Embeddable
    public static class Id implements Serializable{
        @Column(name = "id_article")
        private int idArticle;
        @Column (name = "id_appro")
        private int idAppro;

        public Id() {
        }

        public int getIdArticle() {
            return idArticle;
        }

        public void setIdArticle(int idArticle) {
            this.idArticle = idArticle;
        }

        public int getIdAppro() {
            return idAppro;
        }

        public void setIdAppro(int idAppro) {
            this.idAppro = idAppro;
        }
    
    }
    
    @EmbeddedId
    private Id id = new Id(); 
    @Column (name = "qte_appro")
    private int quantiteAppro;
    
    // relation principale LigneApprovisionnement (many) -> Article (one)
    // implémentée par la clé étrangère : idArticle (LigneApprovisionnement (many) -> Article (one)
    // produitId est en même temps élément de la clé primaire composite
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur
    @ManyToOne
    @JoinColumn(name="id_article", insertable = false , updatable = false)
    private Article article;
    
    // relation principale LigneApprovisionnement -> Approvisionnement
    // implémentée par la clé étrangère : idAppro (LigneApprovisionnement (many) -> Approvisionnement (one)
    // approId est en même temps élément de la clé primaire composite
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur
    @ManyToOne
    @JoinColumn (name = "id_appro", insertable = false , updatable = false)
    private Approvisionnement approvisionnement;

    public LigneApprovisionnement() {
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public int getQuantiteAppro() {
        return quantiteAppro;
    }

    public void setQuantiteAppro(int quantiteAppro) {
        this.quantiteAppro = quantiteAppro;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Approvisionnement getApprovisionnement() {
        return approvisionnement;
    }

    public void setApprovisionnement(Approvisionnement approvisionnement) {
        this.approvisionnement = approvisionnement;
    }
    
            
}
