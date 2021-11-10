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

import com.gft.starter.api.entities.Starters;
import com.gft.starter.api.event.RecursoCriadoEvent;
import com.gft.starter.api.repositories.StartersRepository;
import com.gft.starter.api.service.StartersService;

@RestController
@RequestMapping("/starters")
public class StartersController {

	@Autowired
	private StartersRepository startersRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private StartersService startersService;

	@GetMapping
	public List<Starters> listar() {
		return startersRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Starters> criar(@Valid @RequestBody Starters starters, HttpServletResponse response) {
		Starters startersSalva = startersRepository.save(starters);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, startersSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(startersSalva);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public Starters buscarPeloId(Long id) throws Exception {
		Optional<Starters> starters = startersRepository.findById(id);
		if (starters.isEmpty()) {
			throw new Exception("Starter não encontrado");
		}
		return starters.get();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		startersRepository.deleteById(id);
	}

	//PRECISA MANTER O ID DESCRIMINADO QUANDO FOR EDITAR
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('INSTRUTOR')")
	public ResponseEntity<Starters> atualizar(@PathVariable Long id, @Valid @RequestBody Starters starters){
		Starters startersSalva = startersService.atualizar(id, starters);
		return ResponseEntity.ok(startersSalva);
	}
}
