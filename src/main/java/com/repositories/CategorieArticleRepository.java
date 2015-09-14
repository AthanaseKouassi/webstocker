/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositories;

import com.entities.CategorieArticle;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Athanase
 */
public interface CategorieArticleRepository extends CrudRepository<CategorieArticle, Integer> {
    public List<CategorieArticle> findByLibelleCategorie(String libelleCategorie);
}
