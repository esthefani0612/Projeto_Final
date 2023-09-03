package br.ficdev.com.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;

@Entity
@Table(name="hora_extra")
public class HoraExtra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dia;
	private String turno;
	private LocalTime hora_inicio;
	private LocalTime hora_termino;
	
	@Max(value=24)
	private Integer hora_total;

	//construtor
	public HoraExtra() {
		super();
	}

	
	//getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public LocalTime getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public LocalTime getHora_termino() {
		return hora_termino;
	}

	public void setHora_termino(LocalTime hora_termino) {
		this.hora_termino = hora_termino;
	}

	public Integer getHora_total() {
		return hora_total;
	}

	public void setHora_total(Integer hora_total) {
		this.hora_total = hora_total;
	}
	
	
}