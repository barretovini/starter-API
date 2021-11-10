package com.gft.starter.api.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class Linguagem {
	
	@NotEmpty
	private String nomeLinguagem;
	

	public String getNomeLinguagem() {
		return nomeLinguagem;
	}

	public void setNomeLinguagem(String nomeLinguagem) {
		this.nomeLinguagem = nomeLinguagem;
	}

}