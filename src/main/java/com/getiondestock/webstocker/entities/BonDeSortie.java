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
public class BonDeSortie implements Serializable{
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private int id;
    @Temporal (TemporalType.DATE)
    @Column ( name ="datebs")
    private Date date;    
    private String motif;
    
    // relation principale BonDeSortie (many) -> Client (one)
    // implémentée par une clé étrangère (id_client) dans Client
    // 1 BonDeSortie n'a pas nécessairement 1 Client (nullable=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_client", nullable = true)
    private Client client;  
        
    // relation principale BonDeSortie (many) -> Magasin (one)
    // implémentée par une clé étrangère (id_client) dans Magasin
    // 1 BonDeSortie n'a pas nécessairement 1 Magasin (nullable=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_magasin", nullable = true)
    private Magasin  magasin;  
    
    // relation BonDeSortie (one) -> LigneBonDeSortie (many)
    // inverse de la relation existante LigneBonDeSortie (many) -> BonDeSortie (one)
    // cascade suppression BonDeSortie -> supression LigneBonDeSortie
    @OneToMany(mappedBy = "bondesortie", cascade = {CascadeType.REMOVE})
    private List<LigneBonDeSortie> article;
    
    public BonDeSortie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public List<LigneBonDeSortie> getArticle() {
        return article;
    }

    public void setArticle(List<LigneBonDeSortie> article) {
        this.article = article;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

}
