package com.gft.starter.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.starter.api.entities.Desafios;
import com.gft.starter.api.repositories.DesafiosRepository;

@Service
public class DesafiosService {

	@Autowired
	private DesafiosRepository desafiosRepository;

	public Desafios atualizar(Long id, Desafios desafios) {
		Desafios desafiosSalva = buscarPessoaPeloId(id);
		BeanUtils.copyProperties(desafios, desafiosSalva, "id");
		return desafiosRepository.save(desafios);
	}

	public Desafios buscarPessoaPeloId(Long id) {
		Optional<Desafios> desafiosSalva = desafiosRepository.findById(id);
		if (desafiosSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return desafiosSalva.get();
	}

}
