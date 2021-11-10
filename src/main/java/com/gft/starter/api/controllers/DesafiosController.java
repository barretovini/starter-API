package com.gft.starter.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gft.starter.api.entities.Desafios;
import com.gft.starter.api.event.RecursoCriadoEvent;
import com.gft.starter.api.repositories.DesafiosRepository;
import com.gft.starter.api.service.DesafiosService;

@RestController
@RequestMapping("/desafios")
public class DesafiosController {

	@Autowired
	private DesafiosService desafiosService;
	@Autowired
	private DesafiosRepository desafiosRepository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Desafios> listar() {
		return desafiosRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Desafios> criar(@Valid @RequestBody Desafios desafios, HttpServletResponse response) {
		Desafios desafiosSalva = desafiosRepository.save(desafios);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, desafiosSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(desafiosSalva);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public Desafios buscarPeloId(Long id) throws Exception {

		Optional<Desafios> desafios = desafiosRepository.findById(id);

		if (desafios.isEmpty()) {
			throw new Exception("Desafio não encontrado");
		}
		return desafios.get();

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		desafiosRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public ResponseEntity<Desafios> atualizar(@PathVariable Long id, @Valid @RequestBody Desafios desafios) {
		Desafios desafiosSalva = desafiosService.atualizar(id, desafios);
		return ResponseEntity.ok(desafiosSalva);
	}
}
