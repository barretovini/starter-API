package com.gft.starter.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EntregaDesafio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String repositorio;
	
	@NotNull
	@ManyToOne
	private Starters starters;
	
	@NotNull
	@ManyToOne
	private Desafios desafios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Starters getStarters() {
		return starters;
	}

	public void setStarters(Starters starters) {
		this.starters = starters;
	}

	public Desafios getDesafios() {
		return desafios;
	}

	public void setDesafios(Desafios desafios) {
		this.desafios = desafios;
	}

	public String getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(String repositorio) {
		this.repositorio = repositorio;
	}

	
}
