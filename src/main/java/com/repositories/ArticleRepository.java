/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositories;

import com.entities.Article;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Athanase
 */
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    public Article update(Article art);
    public Article findByReferenceOrLibelleArticle(String valeurDeRecherche);
    public List<Article> findByReference(String reference);
    public List<Article> findByLibelleArticle(String libelleArticle);
    //public List<Article> findByDateCreation(Date dateCreation);
}
