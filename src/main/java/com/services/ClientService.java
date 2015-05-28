/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entities.Client;
import java.util.List;

/**
 *
 * @author Athanase
 */
public interface ClientService {
    public Client creer(Client c);
    public Client update(Client c);
    public Client findByNomAndPrenom(String nom, String prenom);
    public Client findByNomAndTelephone(String nom, String telephone);
    public List<Client> findByNom(String nom);
    
}
