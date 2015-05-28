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
public class Client implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    private String ville;
    private String commune;
    private String rue;
    private String telephone;
    private String email;
    
    // relation inverse Client (one) -> Facture (many) de la relation Facture (many) -> Client(one)
    // cascade insertion Client -> insertion Facture
    // cascade maj Client -> maj Farcture
    // cascade suppression Client -> suppression Facture
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Facture> facture;
    
    // relation inverse Client (one) -> BonDeSortie (many) de la relation BonDeSortie (many) -> Client(one)
    // cascade insertion Client -> insertion BonDeSortie
    // cascade maj Client -> maj BonDeSortie
    // cascade suppression Client -> suppression BonDeSortie
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<BonDeSortie> bondesortie;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Facture> getFacture() {
        return facture;
    }

    public void setFacture(List<Facture> facture) {
        this.facture = facture;
    }

    public List<BonDeSortie> getBondesortie() {
        return bondesortie;
    }

    public void setBondesortie(List<BonDeSortie> bondesortie) {
        this.bondesortie = bondesortie;
    }
    
}
