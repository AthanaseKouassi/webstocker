/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Facture;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Athanase
 */
public interface FactureService {
    public Facture getOne(int id)  ;      
    public Facture creer(Facture fac)  ;    
    public Facture update(Facture fac) ;    
    public void delete(int id) ; 
    public List<Facture> findByDate(Date datefacture);
    public List<Facture> findByDateBetween(Date datefacture0, Date datefacture1);
}
