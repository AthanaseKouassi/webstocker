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
import javax.persistence.Table;

/**
 *
 * @author Athanase
 */
@Entity
@SuppressWarnings("PersistenceUnitPresent")
@Table ( name = "categorie_article")
public class CategorieArticle implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column ( name = "lib_categorie", length = 80)
    private String libelleCategorie;
    
    // relation inverse CategorieArticle (one) -> Article (many) de la relation Article (many) -> CategorieArticle(one)
    // cascade insertion CategorieArticle -> insertion Articles
    // cascade maj CategorieArticle -> maj Articles
    // cascade suppression CategorieArticle -> suppression Articles
    @OneToMany (mappedBy = "categorieArticle", cascade = CascadeType.ALL)
    private List<Article> article ;

    public CategorieArticle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelleCategorie() {
        return libelleCategorie;
    }

    public void setLibelleCategorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
    
    
}
