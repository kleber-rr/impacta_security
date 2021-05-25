package com.osvaldoprosper.curso.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osvaldoprosper.curso.security.domain.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

	@Query("SELECT e FROM Especialidade e WHERE e.titulo like :titulo%")
	Page<Especialidade> findAllByTitulo(String titulo, Pageable pageable);
	
	Page<Especialidade> findByTituloContaining(String titulo, Pageable pageable);

}
