package br.ficdev.com.model;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="perito")
public class Perito {

	@Id
	@CPF
	private String cpf;
	
	@Column(length = 100, name ="nome_completo")
	private String nome;
	
	@NotBlank
	@Column(length = 60, nullable = false) //na tabela a coluna username não pode estar vazia
	private String username;
	
	@NotBlank	//esta anotação significa que estes campos não podem estar vazios
	@Column(length = 100, nullable = false) //na tabela a coluna senha não pode estar vazia
	private String senha;
	
	@Pattern(regexp = "\\d{10,11}", message = "Número de celular inválido")
	@Column(name="celular")
	private String telefone;
	
	@Column
	@Email
	private String email;
	
	@OneToMany(mappedBy = "perito_cpf", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Perito_HoraExtra> horaextra;



	
	//construtor vazio
	public Perito() {
		super();
	}

	//getters e setters
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
