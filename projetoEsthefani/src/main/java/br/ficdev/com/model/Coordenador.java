package br.ficdev.com.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="coordenador_escalas")
public class Coordenador {
	@Id
	@CPF
	private String cpf;

	@Column(name="nome_completo")
	private String nome;

	@NotBlank
	@Column(length = 100, nullable = false)
	private String senha;
	@NotBlank
	@Column(length = 60, nullable = false)
	private String username;
	
	@Email
	private String email;
	
	@Pattern(regexp = "\\d{10,11}", message = "Número de celular inválido")
	private String telefone;



	//construtor
	public Coordenador() {
		super();
	}



	//getters e setters
	

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
	
}
