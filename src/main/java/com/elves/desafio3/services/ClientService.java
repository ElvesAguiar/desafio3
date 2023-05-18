package com.elves.desafio3.services;

import com.elves.desafio3.dto.ClientDTO;
import com.elves.desafio3.entities.Client;
import com.elves.desafio3.resources.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService {


    @Autowired
    ClientResource resource;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll(){
        List<Client> list = resource.findAll();
        List<ClientDTO> result= list.stream().map( x -> new ClientDTO(x)).toList();

        return result;

    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        return new ClientDTO(resource.findById(id).get());
    }

    @Transactional
    public ClientDTO insert(Client client){
        return new ClientDTO(client= resource.save(client));
    }

    @Transactional
    public ClientDTO update(Client client, Long id){

        Client entity = resource.getReferenceById(id);

        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setBirthDate(client.getBirthDate());
        entity.setIncome(client.getIncome());
        entity.setChildren(client.getChildren());

        return new ClientDTO(resource.save(entity));
    }

    @Transactional
    public void delete(Long id){
        resource.deleteById(id);
    }
}
