package com.elves.desafio3.controllers;

import com.elves.desafio3.dto.ClientDTO;
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
    public List<ClientDTO> findAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}")
    public ClientDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping

    public ClientDTO insert(@RequestBody Client entity) {
        return service.insert(entity);
    }

    @PutMapping(value = "/{id}")
    public ClientDTO update(@RequestBody Client entity, @PathVariable Long id){
        return service.update(entity, id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
