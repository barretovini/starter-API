package com.gft.starter.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.starter.api.entities.Starters;
import com.gft.starter.api.repositories.StartersRepository;

@Service
public class StartersService {

	@Autowired
	private StartersRepository startersRepository;
	
	public Starters atualizar(Long id, Starters starters) {
		Starters startersSalva = buscarStarterPeloId(id);
		BeanUtils.copyProperties(starters, startersSalva, "id");
		return startersRepository.save(starters);
	}
	
	public Starters buscarStarterPeloId(Long id) {
		Optional<Starters> startersSalva = startersRepository.findById(id);
		if(startersSalva.isEmpty() ) {
			throw new EmptyResultDataAccessException(1);
		}
		return startersSalva.get();
	}

	
}
