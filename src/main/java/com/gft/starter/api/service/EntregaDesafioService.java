package com.gft.starter.api.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.starter.api.entities.EntregaDesafio;
import com.gft.starter.api.entities.Starters;
import com.gft.starter.api.repositories.EntregaDesafioRepository;
import com.gft.starter.api.repositories.StartersRepository;
import com.gft.starter.api.service.exception.StarterInexistenteException;


@Service
public class EntregaDesafioService {
	
	@Autowired
	private StartersRepository startersRepository;
	
	//@Autowired
	//private DesafiosRepository desafiosRepository;

	@Autowired
	private EntregaDesafioRepository entregaDesafioRepository;
	
	
	public EntregaDesafio atualizar(Long id, EntregaDesafio entregaDesafio) {
		EntregaDesafio EntregaDesafioSalva = buscarStarterPeloId(id);
		BeanUtils.copyProperties(entregaDesafio, EntregaDesafioSalva, "id");
		return entregaDesafioRepository.save(entregaDesafio);
	}
	
	private EntregaDesafio buscarStarterPeloId(Long id) {
		Optional<EntregaDesafio> entregaDesafioSalva = entregaDesafioRepository.findById(id);
		if(entregaDesafioSalva.isEmpty() ) {
			throw new EmptyResultDataAccessException(1);
		}
		return entregaDesafioSalva.get();
	}

	
	public EntregaDesafio salvar(@Valid EntregaDesafio entregaDesafio) {
		
		Optional<Starters> starters = startersRepository.findById(entregaDesafio.getStarters().getId());
		if(starters.isEmpty()) {
			throw new StarterInexistenteException();
		}
		return entregaDesafioRepository.save(entregaDesafio);
	}

}
