package com.gft.starter.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.starter.api.entities.EntregaDesafio;

@Repository
public interface EntregaDesafioRepository extends JpaRepository<EntregaDesafio, Long> {
}

