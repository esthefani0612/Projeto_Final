package br.ficdev.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import br.ficdev.com.model.HoraExtra;
import br.ficdev.com.model.Perito;

@Repository
@EnableJpaRepositories
public interface HoraExtraRepositoty extends JpaRepository<HoraExtra, Long> {
	
	List<HoraExtra> findByPerito(Perito perito);
}



