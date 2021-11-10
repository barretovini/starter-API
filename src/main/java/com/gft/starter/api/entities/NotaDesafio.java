package com.gft.starter.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class NotaDesafio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private EntregaDesafio entregaDesafio;

	@Min(1)
	@Max(3)
	private int notaQuantidade;

	@Min(1)
	@Max(3)
	private int notaQualidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntregaDesafio getEntregaDesafio() {
		return entregaDesafio;
	}

	public void setEntregaDesafio(EntregaDesafio entregaDesafio) {
		this.entregaDesafio = entregaDesafio;
	}

	public int getNotaQuantidade() {
		return notaQuantidade;
	}

	public void setNotaQuantidade(int notaQuantidade) {
		this.notaQuantidade = notaQuantidade;
	}

	public int getNotaQualidade() {
		return notaQualidade;
	}

	public void setNotaQualidade(int notaQualidade) {
		this.notaQualidade = notaQualidade;
	}


}