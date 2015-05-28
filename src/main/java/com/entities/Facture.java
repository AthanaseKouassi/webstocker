/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Facture implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; 
    @Temporal(TemporalType.DATE)   
    @Column (name = "date_facture")    
    private Date date;
    
    // relation principale Facture (many) -> Remise (one)
    // implémentée par une clé étrangère (id_remise) dans Remise
    // 1 Facture peut ne pas avoir nécessairement 1 Remise (nullable=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_remise", nullable = true)
    private Remise remise;    

    // relation Facture (one) -> LigneFacture (many)
    // inverse de la relation existante LigneFacture (many) -> Facture (one)
    // cascade suppression Facture -> supression LigneFacture
    @OneToMany(mappedBy = "facture", cascade = { CascadeType.REMOVE })
    private List<LigneFacture> article ;

    // relation principale Facture (many) -> Client (one)
    // implémentée par une clé étrangère (id_client) dans Client
    // 1 Facture a nécessairement 1 Client (nullable=false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_client", nullable = false)
    private Client client;
    
    // relation Facture (one) -> Reglement (many)
    // inverse de la relation existante Reglement (many) -> Facture (one)
    // cascade suppression Facture -> supression Reglement
    @OneToMany(mappedBy = "facture", cascade = {CascadeType.REMOVE})
    private List<Reglement> reglement;
    
    public Facture() {
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

    public List<LigneFacture> getArticle() {
        return article;
    }

    public void setArticle(List<LigneFacture> article) {
        this.article = article;
    }

    public Remise getRemise() {
        return remise;
    }

    public void setRemise(Remise remise) {
        this.remise = remise;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }   

    public List<Reglement> getReglement() {
        return reglement;
    }

    public void setReglement(List<Reglement> reglement) {
        this.reglement = reglement;
    }
   
}
