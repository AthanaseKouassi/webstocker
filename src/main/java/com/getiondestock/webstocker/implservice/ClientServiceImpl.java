/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.implservice;

import com.entities.Client;
import com.repositories.ClientRepository;
import com.services.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Athanase
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository repository;
   
    @Override
    public Client creer(Client c) {
        return repository.save(c);
    }

    @Override
    public Client update(Client c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client findByNomAndPrenom(String nom, String prenom) {
        return repository.findByNomAndPrenom(nom, prenom);
    }

    @Override
    public Client findByNomAndTelephone(String nom, String telephone) {
        return repository.findByNomAndTelephone(nom, telephone);
    }

    @Override
    public List<Client> findByNom(String nom) {
       return (List<Client>)repository.findByNom(nom);
    }
    
}
