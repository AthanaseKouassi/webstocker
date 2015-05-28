/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implservice;

import com.entities.Facture;
import com.repositories.FactureRepository;
import com.services.FactureService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Athanase
 */
@Service
public class FactureServiceImpl implements FactureService{
    
    @Autowired
    FactureRepository repository;

    @Override
    public Facture getOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public Facture creer(Facture fac) {
       return repository.save(fac);
    }

    @Override
    public Facture update(Facture fac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Facture> findByDate(Date datefacture) {
       return (List<Facture>)repository.findByDate(datefacture);
    }

    @Override
    public List<Facture> findByDateBetween(Date datefacture0, Date datefacture1) {
        return (List<Facture>)repository.findByDateBetween(datefacture0, datefacture1);
    }

}
