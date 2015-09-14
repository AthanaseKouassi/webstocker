/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Athanase
 */
@Entity
@SuppressWarnings("PersistenceUnitPresent")
public class Utilisateur implements Serializable {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private int id;
    @Column ( name = "nom_util")
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String fonction;
    private String droitAcces;
    private String motdePasse;

    public Utilisateur() {
    }

    public Utilisateur(String nomUtilisateur, String prenomUtilisateur, String fonction, String droitAcces, String motdePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.fonction = fonction;
        this.droitAcces = droitAcces;
        this.motdePasse = motdePasse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getDroitAcces() {
        return droitAcces;
    }

    public void setDroitAcces(String droitAcces) {
        this.droitAcces = droitAcces;
    }

    public String getMotdePasse() {
        return motdePasse;
    }

    public void setMotdePasse(String motdePasse) {
        this.motdePasse = motdePasse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nomUtilisateur=" + nomUtilisateur 
                + ", prenomUtilisateur=" + prenomUtilisateur + ", fonction=" 
                + fonction + ", droitAcces=" + droitAcces + ", motdePasse=" + motdePasse + '}';
    }
    
    
    
}
