package br.ficdev.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import br.ficdev.com.model.EscalaTrabalho;

@Repository
@EnableJpaRepositories
public interface EscalaRepository extends JpaRepository<EscalaTrabalho, Long> {
    
}
