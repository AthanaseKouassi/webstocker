/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Fournisseur implements Serializable {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private int id;
    @Column ( name = "nomfour")
    private String nomFournisseur;
    @Column ( name = "vilfour")
    private String ville;
    private String pays;
    @Column ( name = "ruefour")
    private String rue;
    @Column ( name = "telfour")
    private String telephoneFour;
    private String emailFour;
    
    // relation inverse Fournisseur (one) -> Approvisionnement (many) de la relation Article (many) -> Fournisseur(one)
    // cascade insertion Fournisseur -> insertion Approvisionnement
    // cascade maj Fournisseur -> maj Approvisionnement
    // cascade suppression Fournisseur -> suppression Approvisionnement
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    private List<Approvisionnement> approvisionnement;

    public Fournisseur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getTelephoneFour() {
        return telephoneFour;
    }

    public void setTelephoneFour(String telephoneFour) {
        this.telephoneFour = telephoneFour;
    }

    public String getEmailFour() {
        return emailFour;
    }

    public void setEmailFour(String emailFour) {
        this.emailFour = emailFour;
    }

    public List<Approvisionnement> getApprovisionnement() {
        return approvisionnement;
    }

    public void setApprovisionnement(List<Approvisionnement> approvisionnement) {
        this.approvisionnement = approvisionnement;
    }

}
