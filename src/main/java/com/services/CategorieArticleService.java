/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.CategorieArticle;
import java.util.List;

/**
 *
 * @author Athanase
 */
public interface CategorieArticleService {
    public CategorieArticle getOne(int id)  ;    
    public List<CategorieArticle> getAll() ;    
    public CategorieArticle creer(CategorieArticle cat)  ;    
    public CategorieArticle update(CategorieArticle catt) ;    
    public void delete(int id) ;    
    public List<CategorieArticle> findByLibelleCategorie(String libelleCategorie);
}
