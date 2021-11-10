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

import com.gft.starter.api.entities.NotaDesafio;
import com.gft.starter.api.event.RecursoCriadoEvent;
import com.gft.starter.api.repositories.NotaDesafioRepository;
import com.gft.starter.api.service.NotaDesafioService;

@RestController
@RequestMapping("/notas")
public class NotaDesafioController {

	@Autowired
	private NotaDesafioRepository notaDesafioRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private NotaDesafioService notaDesafioService;

	@GetMapping
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public List<NotaDesafio> listar() {
		return notaDesafioRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<NotaDesafio> criar(@Valid @RequestBody NotaDesafio notaDesafio, HttpServletResponse response) {
		NotaDesafio notaDesafioSalva = notaDesafioRepository.save(notaDesafio);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, notaDesafioSalva.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(notaDesafioSalva);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public NotaDesafio buscarPeloId(Long id) throws Exception {

		Optional<NotaDesafio> notaDesafio = notaDesafioRepository.findById(id);

		if (notaDesafio.isEmpty()) {
			throw new Exception("Nota não encontrada");
		}
		return notaDesafio.get();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		notaDesafioRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<NotaDesafio> atualizar(@PathVariable Long id, @Valid @RequestBody NotaDesafio notaDesafio){
		NotaDesafio notaDesafioSalva = notaDesafioService.atualizar(id, notaDesafio);
		return ResponseEntity.ok(notaDesafioSalva);
	}
}
