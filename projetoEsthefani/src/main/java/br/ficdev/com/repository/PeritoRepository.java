package br.ficdev.com.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import br.ficdev.com.model.Perito;
@Repository
@EnableJpaRepositories
public interface PeritoRepository extends JpaRepository<Perito, String>{
	@Query(value="select * from perito where username = :username and senha = :senha", nativeQuery = true)
	public Perito loginP(String username, String senha);
	
	Optional<Perito> findById(String cpf);

}
