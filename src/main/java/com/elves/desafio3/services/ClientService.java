package com.elves.desafio3.services;

import com.elves.desafio3.dto.ClientDTO;
import com.elves.desafio3.entities.Client;
import com.elves.desafio3.exceptions.DomainException;
import com.elves.desafio3.resources.ClientResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
public class ClientService {


    @Autowired
    ClientResource resource;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){

        Page<Client> list = resource.findAll(pageable);

        return list.map(x -> new ClientDTO(x));

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
    public ClientDTO insert(ClientDTO dto){

        Client entity = new Client();
        entity.setId(dto.getId());
        copyDtoToEntity(dto, entity);

        return new ClientDTO(resource.save(entity));
    }
    @Transactional
    public ClientDTO update(ClientDTO dto, Long id){

        try {
            Client entity = resource.getReferenceById(id);
            copyDtoToEntity(dto, entity);

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

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());
    }

}
