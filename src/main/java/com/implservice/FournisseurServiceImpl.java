/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implservice;

import com.entities.Fournisseur;
import com.repositories.FournisseurRepository;
import com.services.FournisseurService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Athanase
 */
@Service
public class FournisseurServiceImpl implements FournisseurService{
    
    @Autowired
    FournisseurRepository repository;

    @Override
    public Fournisseur getOne(int id) {
       return repository.findOne(id);
    }

    @Override
    public List<Fournisseur> getAll() {
        return (List<Fournisseur>)repository.findAll();
    }

    @Override
    public Fournisseur creer(Fournisseur f) {
        return repository.save(f);
    }

    @Override
    public Fournisseur update(Fournisseur f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Fournisseur> findByNomFournisseur(String nomFournisseur) {
        return (List<Fournisseur>)repository.findByNomFournisseur(nomFournisseur);
    }
    
}
