package com.elves.desafio3.controllers;

import com.elves.desafio3.dto.ClientDTO;
import com.elves.desafio3.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    ClientService service;

    @RequestMapping
    public ResponseEntity<List<ClientDTO>>  findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping

    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO entity) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(entity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO entity, @PathVariable Long id){
        return ResponseEntity.ok().body(service.update(entity, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
