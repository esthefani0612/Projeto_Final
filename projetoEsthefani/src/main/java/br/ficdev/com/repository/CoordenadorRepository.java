package br.ficdev.com.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import br.ficdev.com.model.Coordenador;
import br.ficdev.com.model.Perito;

@Repository
@EnableJpaRepositories
public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {
    
	@Query(value="select * from coordenador_escalas where username = :username and senha = :senha", nativeQuery = true)
	public Coordenador login(String username, String senha);
	
	Optional<Coordenador> findByCpf(String cpf);
	
}
