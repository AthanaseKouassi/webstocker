/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implservice;

import com.entities.CategorieArticle;
import com.repositories.CategorieArticleRepository;
import com.services.CategorieArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Athanase
 */
public class CategorieArticleServiceImpl implements CategorieArticleService {
    
    @Autowired
    CategorieArticleRepository repository;
    
    @Override
    public CategorieArticle getOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<CategorieArticle> getAll() {
        return (List<CategorieArticle>)repository.findAll();
    }

    @Override
    public CategorieArticle creer(CategorieArticle cat) {
        return repository.save(cat);
    }

    @Override
    public CategorieArticle update(CategorieArticle catt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategorieArticle> findByLibelleCategorie(String libelleCategorie) {
        return (List<CategorieArticle>)repository.findByLibelleCategorie(libelleCategorie);
    }
    
}
