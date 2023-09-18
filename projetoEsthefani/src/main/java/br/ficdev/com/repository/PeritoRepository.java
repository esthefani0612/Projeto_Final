package br.ficdev.com.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.ficdev.com.model.Perito;

@Repository
@EnableJpaRepositories
public interface PeritoRepository extends JpaRepository<Perito, String>{

	Optional<Perito> findById(String cpf);

	UserDetails findByCpf(Object cpf);

}
