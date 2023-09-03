package br.ficdev.com.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private Integer telefone;
	
	@Column
	@Email
	private String email;
	
	@ManyToOne
	@JoinColumn
	private EscalaTrabalho escala_id;

	@ManyToOne
	@JoinColumn
	private Coordenador coordenador_id;
	
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


	public Coordenador getCoordenador_id(){
		return coordenador_id;
	}

	public void setCoordenador_id(Coordenador coordenador_id){
		this.coordenador_id=coordenador_id;
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


	public Integer getTelefone() {
		return telefone;
	}


	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
