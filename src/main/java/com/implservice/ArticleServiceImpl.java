/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implservice;

import com.entities.Article;
import com.repositories.ArticleRepository;
import com.services.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Athanase
 */
@Service
public class ArticleServiceImpl implements ArticleService{
    
    @Autowired
    ArticleRepository repository;

    @Override
    public Article getOne(int id) {
        return repository.findOne(id);              
    }

    @Override
    public List<Article> getAll() {
        return (List<Article>)repository.findAll();
    }

    @Override
    public Article creer(Article a) {
        return repository.save(a);
    }
   
    @Override
    public Article update(Article art) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Article> findByReference(String reference) {
       return (List<Article>)repository.findByReference(reference);
    }

    @Override
    public List<Article> findByLibelleArticle(String libelleArticle) {
       return (List<Article>)repository.findByLibelleArticle(libelleArticle);
    }

//    @Override
//    public List<Article> findByDateCreation(Date dateCreation) {
//        return (List<Article>)repository.findByDateCreation(dateCreation);
//    }

    @Override
    public Article findByReferenceOrLibelleArticle(String valeurDeRecherche) {
        return repository.findByReferenceOrLibelleArticle(valeurDeRecherche);
    }
    
}
