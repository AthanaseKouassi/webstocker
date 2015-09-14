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
@SuppressWarnings ("PersistenceUnitPresent")
public class Approvisionnement implements Serializable{
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int id;
    @Temporal (TemporalType.DATE)
    @Column (name = "date_appro")
    private Date dateAppro;
    
    // relation Approvisionnement (one) -> LigneApprovisionnement (many)
    // inverse de la relation existante LigneApprovisionnement (many) -> Approvisionnement (one)
    // cascade suppression Approvisionnement -> supression LigneApprovisionnement
    @OneToMany(mappedBy = "approvisionnement", cascade = { CascadeType.REMOVE} )
    private List<LigneApprovisionnement> article;
    
    // relation principale Approvisionnement (many) -> Fournisseur (one)
    // implémentée par une clé étrangère (id_four) dans Approvisionnement
    // 1 Approvisionnement a nécessairement 1 Fournisseur (nullable=false)
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_four", nullable = false)
    private Fournisseur fournisseur;
    
    //constructeur par defaut
    public Approvisionnement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateAppro() {
        return dateAppro;
    }

    public void setDateAppro(Date dateAppro) {
        this.dateAppro = dateAppro;
    }

    public List<LigneApprovisionnement> getArticle() {
        return article;
    }

    public void setArticle(List<LigneApprovisionnement> article) {
        this.article = article;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    
}
