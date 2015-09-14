/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Athanase
 */
@Entity
@SuppressWarnings("PersistenceUnitPresent")
public class Remise implements Serializable{
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private double valeurRemise;
    private double pourcentage;
    
    // relation inverse Remise (one) -> Facture (many) de la relation Facture (many) -> Remise(one)
    // cascade insertion Remise -> insertion Facture
    // cascade maj Remise -> maj Facture
    // cascade suppression Remise -> suppression Facture
    @OneToMany(mappedBy = "remise", cascade = CascadeType.ALL)
    private List<Facture> facture;

    public Remise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValeurRemise() {
        return valeurRemise;
    }

    public void setValeurRemise(double valeurRemise) {
        this.valeurRemise = valeurRemise;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public List<Facture> getFacture() {
        return facture;
    }

    public void setFacture(List<Facture> facture) {
        this.facture = facture;
    }
    
    

}
