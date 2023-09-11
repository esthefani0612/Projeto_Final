package br.ficdev.com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Perito_HoraExtra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private HoraExtra hora_id;
	
	@ManyToOne
	@JoinColumn
	private Perito perito_cpf;

	
	public Perito_HoraExtra() {
		super();
	}

	//getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HoraExtra getHora_id() {
		return hora_id;
	}

	public void setHora_id(HoraExtra hora_id) {
		this.hora_id = hora_id;
	}

	public Perito getPerito_cpf() {
		return perito_cpf;
	}

	public void setPerito_cpf(Perito perito_cpf) {
		this.perito_cpf = perito_cpf;
	}
	
	
	
	
	
}
