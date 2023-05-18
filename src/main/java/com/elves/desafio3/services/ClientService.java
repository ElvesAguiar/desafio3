package com.elves.desafio3.services;

import com.elves.desafio3.entities.Client;
import com.elves.desafio3.resources.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ClientService {


    @Autowired
    ClientResource resource;

    @Transactional(readOnly = true)
    public List<Client> findAll(){
        return resource.findAll();
    }

    @Transactional(readOnly = true)
    public Client findById(Long id){
        return resource.findById(id).get();
    }

    @Transactional
    public Client insert(Client client){
        return client= resource.save(client);
    }

    @Transactional
    public Client update(Client client, Long id){

        Client entity = resource.getReferenceById(id);

        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setBirthDate(client.getBirthDate());
        entity.setIncome(client.getIncome());
        entity.setChildren(client.getChildren());

        return resource.save(entity);
    }

    @Transactional
    public void delete(Long id){
        resource.deleteById(id);
    }
}
