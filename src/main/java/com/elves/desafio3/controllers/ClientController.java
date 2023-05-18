package com.elves.desafio3.controllers;

import com.elves.desafio3.entities.Client;
import com.elves.desafio3.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientService service;

    @RequestMapping
    public List<Client> findAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}")
    public Client findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping

    public Client insert(@RequestBody Client entity) {
        return service.insert(entity);
    }

    @PutMapping(value = "/{id}")
    public Client update(@RequestBody Client entity, @PathVariable Long id){
        return service.update(entity, id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
