/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositories;


import com.entities.Client;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Athanase
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {
    public Client findByNomAndPrenom(String nom, String prenom);
    public Client findByNomAndTelephone(String nom, String telephone);
    public List<Client> findByNom(String nom);
}
