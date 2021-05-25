package com.osvaldoprosper.curso.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osvaldoprosper.curso.security.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.email like :email")
	Usuario findByEmailQuery(@Param("email") String email);
	
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.email like :search%")
	Page<Usuario> findAllByEmail(String search, Pageable pageable);
	
	Page<Usuario> findByEmailContaining(String email, Pageable pageable);

	@Query("SELECT DISTINCT u "
			+ "FROM Usuario u "
			+ "JOIN u.perfis p "
			+ "WHERE u.email like :search% OR p.desc like :search%")
	Page<Usuario> findByEmailOrPerfil(String search, Pageable pageable);
}
