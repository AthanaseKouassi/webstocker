/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositories;

import com.entities.Fournisseur;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Athanase
 */
public interface FournisseurRepository extends CrudRepository<Fournisseur, Integer>{
     public List<Fournisseur> findByNomFournisseur(String nomFournisseur);
}
