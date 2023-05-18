package com.elves.desafio3.resources;

import com.elves.desafio3.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientResource extends JpaRepository<Client,Long> {
}
