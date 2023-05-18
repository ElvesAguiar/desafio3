package com.elves.desafio3.services;

import com.elves.desafio3.dto.ClientDTO;
import com.elves.desafio3.entities.Client;
import com.elves.desafio3.exceptions.DomainException;
import com.elves.desafio3.resources.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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
    try {
        return new ClientDTO(resource.findById(id).get());
    }catch (NoSuchElementException e)   {
        throw new DomainException("Id não encontrado");
    }

    }

    @Transactional
    public ClientDTO insert(ClientDTO client){

        Client entity = new Client();
        entity.setId(client.getId());
        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setBirthDate(client.getBirthDate());
        entity.setIncome(client.getIncome());
        entity.setChildren(client.getChildren());

        return new ClientDTO(resource.save(entity));
    }
    @Transactional
    public ClientDTO update(ClientDTO client, Long id){

        try {

            Client entity = resource.getReferenceById(id);

            entity.setName(client.getName());
            entity.setCpf(client.getCpf());
            entity.setBirthDate(client.getBirthDate());
            entity.setIncome(client.getIncome());
            entity.setChildren(client.getChildren());

            return new ClientDTO(resource.save(entity));

        }catch (NoSuchElementException e){
            throw new DomainException("Id não encontrado");
        }


    }

    @Transactional
    public void delete(Long id){

        try {

            if(resource.existsById(id)) {
                resource.deleteById(id);
            }else throw new NoSuchElementException();

        }catch (NoSuchElementException e){
            throw new DomainException("Id não encontrado");
        }
    }

}
