package com.gft.starter.api.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Starters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nomeStarter;
	
	@Email
	private String emailStarter;
	
	@NotNull
	@Size(min = 4, max = 4)
	private String quatroLetras;
	
	@Pattern(regexp="^\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$")
	private String telefone;
	
	@Embedded
	private Endereco enderecoStarter;
	
	@Embedded
	private Linguagem linguagemStarter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeStarter() {
		return nomeStarter;
	}

	public void setNomeStarter(String nomeStarter) {
		this.nomeStarter = nomeStarter;
	}

	public String getEmailStarter() {
		return emailStarter;
	}

	public void setEmailStarter(String emailStarter) {
		this.emailStarter = emailStarter;
	}

	public String getQuatroLetras() {
		return quatroLetras;
	}

	public void setQuatroLetras(String quatroLetras) {
		this.quatroLetras = quatroLetras;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEnderecoStarter() {
		return enderecoStarter;
	}

	public void setEnderecoStarter(Endereco enderecoStarter) {
		this.enderecoStarter = enderecoStarter;
	}

	public Linguagem getLinguagemStarter() {
		return linguagemStarter;
	}

	public void setLinguagemStarter(Linguagem linguagemStarter) {
		this.linguagemStarter = linguagemStarter;
	}

	
}
