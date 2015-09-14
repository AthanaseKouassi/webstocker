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
@SuppressWarnings("PersistenceUnitPresent")
public class LigneFacture implements Serializable {

    @Embeddable
    public static class Id implements Serializable {
        // composantes de la clé composite
        // pointe sur une Article

        @Column(name = "id_article")
        private int idArticle;
        // pointe sur Facture
        @Column(name = "id_facture")
        private int idFacture;

        public Id() {
        }

        public Id(int idArticle, int idFacture) {
            this.idArticle = idArticle;
            this.idFacture = idFacture;
        }

        public int getIdArticle() {
            return idArticle;
        }

        public void setIdArticle(int idArticle) {
            this.idArticle = idArticle;
        }

        public int getIdFacture() {
            return idFacture;
        }

        public void setIdFacture(int idFacture) {
            this.idFacture = idFacture;
        }

    }

    @EmbeddedId
    private Id id = new Id();
    @Column(name = "qte_facture")
    private int quantiteLigneFacture;

    // relation principale LingeFacture (many) -> Facture (one)
    // implémentée par la clé étrangère : idFacture (LingeFacture (many) -> Facture (one)
    // idFacture est en même temps élément de la clé primaire composite
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur
    @ManyToOne()
    @JoinColumn(name = "id_facture", insertable = false, updatable = false)
    private Facture facture;

    // relation principale LingeFacture (many) -> Article (one)
    // implémentée par la clé étrangère : idArticle (LingeFacture (many) -> Article (one)
    // idArticle est en même temps élément de la clé primaire composite
    // JPA ne doit pas gérer cette clé étrangère (insertable = false, updatable = false) car c'est fait par l'application elle-même dans son constructeur
    @ManyToOne()
    @JoinColumn(name = "id_article", insertable = false, updatable = false)
    private Article article;

    //Constructeur par defaut
    public LigneFacture() {
    }

//    public LigneFacture(int quantiteLigneFacture, Facture facture, Article article) {
//        this.quantiteLigneFacture = quantiteLigneFacture;
//        // les clés étrangères sont fixées par l'application
//        getId().setIdFacture(facture.getId());
//        getId().setIdArticle(article.getId());
//        this.setFacture(facture);
//        this.setArticle( article);
//        facture.getArticle().add(this);
//        article.getFacture().add(this);
//    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public int getQuantiteLigneFacture() {
        return quantiteLigneFacture;
    }

    public void setQuantiteLigneFacture(int quantiteLigneFacture) {
        this.quantiteLigneFacture = quantiteLigneFacture;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
}
