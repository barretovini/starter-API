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

import com.gft.starter.api.entities.EntregaDesafio;
import com.gft.starter.api.event.RecursoCriadoEvent;
import com.gft.starter.api.repositories.EntregaDesafioRepository;
import com.gft.starter.api.service.EntregaDesafioService;

@RestController
@RequestMapping("/entrega")
public class EntregaDesafioController {

	@Autowired
	private EntregaDesafioService entregaDesafioService;
	@Autowired
	private EntregaDesafioRepository entregaDesafioRepository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<EntregaDesafio> listar() {
		return entregaDesafioRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('STARTER')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EntregaDesafio> criar(@Valid @RequestBody EntregaDesafio entregaDesafio, HttpServletResponse response) {
		EntregaDesafio entregaDesafioSalva = entregaDesafioRepository.save(entregaDesafio);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, entregaDesafioSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(entregaDesafioSalva);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public EntregaDesafio buscarPeloId(Long id) throws Exception {
		Optional<EntregaDesafio> entregaDesafio = entregaDesafioRepository.findById(id);
		if (entregaDesafio.isEmpty()) {
			throw new Exception("Entrega não encontrada");
		}
		return entregaDesafio.get();

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		entregaDesafioRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public ResponseEntity<EntregaDesafio> atualizar(@PathVariable Long id, @Valid @RequestBody EntregaDesafio entregaDesafio) {
		EntregaDesafio entregaDesafioSalva = entregaDesafioService.atualizar(id, entregaDesafio);
		return ResponseEntity.ok(entregaDesafioSalva);
	}
}
