/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Athanase
 */
@Entity
@SuppressWarnings("PersistenceUnitPresent")
public class Reglement implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    @Column (name ="date_reglement")    
    private Date date;
    private double montant;
    
    // relation principale Reglement (many) -> Facture (one)
    // implémentée par une clé étrangère (id_facture) dans Reglement
    // 1 Reglement a nécessairement 1 Facture (nullable=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_facture", nullable = true)
    private Facture facture;

    public Reglement() {
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

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
 
}
