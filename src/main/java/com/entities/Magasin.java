/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
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
@SuppressWarnings ("PersistenceUnitPresent")
public class Magasin implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column ( name = "nom_mag")
    private String nomMagasin;
    @Column ( name = "vilmag")
    private String ville;
    @Column ( name = "com_mag")
    private String commune;
    @Column ( name = "rue_mag")
    private String rue;
    private String contact;

    // relation inverse Magasin (one) -> BonDeSortie (many) de la relation BonDeSortie (many) -> Magasin(one)
    // cascade insertion Magasin -> insertion BonDeSortie
    // cascade maj Client -> maj BonDeSortie
    // cascade suppression Magasin -> suppression BonDeSortie
    @OneToMany(mappedBy = "magasin",cascade = CascadeType.ALL)
    private List<BonDeSortie> bondesortie;
    public Magasin() {
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<BonDeSortie> getBondesortie() {
        return bondesortie;
    }

    public void setBondesortie(List<BonDeSortie> bondesortie) {
        this.bondesortie = bondesortie;
    }

}
