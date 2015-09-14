/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Article;
import java.util.List;

/**
 *
 * @author Athanase
 */
public interface ArticleService {
    
    public Article getOne(int id);
    public List<Article> getAll();
    public Article creer(Article art);
    public Article update(Article art);
    public Article findByReferenceOrLibelleArticle(String valeurDeRecherche);
    public List<Article> findByReference(String reference);
    public List<Article> findByLibelleArticle(String libelleArticle);
   // public List<Article> findByDateCreation(Date dateCreation);
    
}
