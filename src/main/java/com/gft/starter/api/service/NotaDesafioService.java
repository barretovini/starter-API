package com.gft.starter.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.starter.api.entities.NotaDesafio;
import com.gft.starter.api.repositories.NotaDesafioRepository;

@Service
public class NotaDesafioService {

	@Autowired
	private NotaDesafioRepository notaDesafioRepository;
	
	public NotaDesafio atualizar(Long id, NotaDesafio notaDesafio) {
		NotaDesafio notaDesafioSalva = buscarPessoaPeloId(id);
		BeanUtils.copyProperties(notaDesafio, notaDesafioSalva, "id");
		return notaDesafioRepository.save(notaDesafio);
	}
	
	public NotaDesafio buscarPessoaPeloId(Long id) {
		Optional<NotaDesafio> notaDesafioSalva = notaDesafioRepository.findById(id);
		if(notaDesafioSalva.isEmpty() ) {
			throw new EmptyResultDataAccessException(1);
		}
		return notaDesafioSalva.get();
	}

	
}
