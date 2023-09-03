package br.ficdev.com.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="escala_de_trablho")
public class EscalaTrabalho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data_inicio;
	private LocalDate data_termino;
	private LocalTime hora_entrada;
	private LocalTime hora_saida;
	private String turno;
	
	@OneToMany (mappedBy = "escala_id", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Perito> atribuicao_perito;

	//construtor vazio
	public EscalaTrabalho() {
		super();
	}

	//setters e getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(LocalDate data_inicio) {
		this.data_inicio = data_inicio;
	}

	public LocalDate getData_termino() {
		return data_termino;
	}

	public void setData_termino(LocalDate data_termino) {
		this.data_termino = data_termino;
	}

	public LocalTime getHora_entrada() {
		return hora_entrada;
	}

	public void setHora_entrada(LocalTime hora_entrada) {
		this.hora_entrada = hora_entrada;
	}

	public LocalTime getHora_saida() {
		return hora_saida;
	}

	public void setHora_saida(LocalTime hora_saida) {
		this.hora_saida = hora_saida;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<Perito> getAtribuicao_perito() {
		return atribuicao_perito;
	}

	public void setAtribuicao_perito(List<Perito> atribuicao_perito) {
		this.atribuicao_perito = atribuicao_perito;
	}
	
	
}
