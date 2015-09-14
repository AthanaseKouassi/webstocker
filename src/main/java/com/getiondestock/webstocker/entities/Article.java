/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Athanase
 */

@Entity
@SuppressWarnings("PersistenceUnitPresent")
public class Article implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "libel_article")
    private String libelleArticle;
    @Column (name ="prix_achat")
    private double prixAchat;
    @Column(name ="prix_vente")
    private double prixVente;
    @Column(name = "qte_stock")
    private int quantiteEnStock;
    @Column(name ="date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
     @Column(name = "reference", length = 80)
    private String reference;
    
    // relation principale Article (many) -> CategorieArticle (one)
    // implémentée par une clé étrangère (categorie_id) dans Article
    // 1 Article a nécessairement 1 CategorieArticle (nullable=false)
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie")
    private CategorieArticle categorieArticle;
    
    // relation Article (one) -> LigneFacture (many)
    // inverse de la relation existante LigneFacture (many) -> Article (one)
    // cascade suppression Article -> supression LigneFacture
    @OneToMany(mappedBy = "article", cascade = { CascadeType.REMOVE })
    private List<LigneFacture> facture ;
    
    // relation Article (one) -> LigneBonDeSortie (many)
    // inverse de la relation existante LigneBonDeSortie (many) -> Article (one)
    // cascade suppression Article -> supression LigneBonDeSortie
    @OneToMany(mappedBy = "article", cascade = { CascadeType.REMOVE })
    private List<LigneBonDeSortie> bondesortie ;
    
    @OneToMany(mappedBy = "article", cascade = { CascadeType.REMOVE })
    private List<LigneApprovisionnement> Approvisionnement ;
    
     
    //constructeur par defaut
    public Article() {
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelleArticle() {
        return libelleArticle;
    }

    public void setLibelleArticle(String libelleArticle) {
        this.libelleArticle = libelleArticle;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public CategorieArticle getCategorieArticle() {
        return categorieArticle;
    }

    public void setCategorieArticle(CategorieArticle categorieArticle) {
        this.categorieArticle = categorieArticle;
    }
    
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public List<LigneFacture> getFacture() {
        return facture;
    }

    public void setFacture(List<LigneFacture> facture) {
        this.facture = facture;
    }

    public List<LigneBonDeSortie> getBondesortie() {
        return bondesortie;
    }

    public void setBondesortie(List<LigneBonDeSortie> bondesortie) {
        this.bondesortie = bondesortie;
    }

    public List<LigneApprovisionnement> getApprovisionnement() {
        return Approvisionnement;
    }

    public void setApprovisionnement(List<LigneApprovisionnement> Approvisionnement) {
        this.Approvisionnement = Approvisionnement;
    }

      
}
