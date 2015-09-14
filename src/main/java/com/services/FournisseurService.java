/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Fournisseur;
import java.util.List;

/**
 *
 * @author Athanase
 */
public interface FournisseurService {
    public Fournisseur getOne(int id)  ;    
    public List<Fournisseur> getAll() ;    
    public Fournisseur creer(Fournisseur f)  ;    
    public Fournisseur update(Fournisseur f) ;    
    public void delete(int id) ; 
    public List<Fournisseur> findByNomFournisseur(String nomFournisseur);
}