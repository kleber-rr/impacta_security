package com.osvaldoprosper.curso.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osvaldoprosper.curso.security.domain.Perfil;
import com.osvaldoprosper.curso.security.domain.Usuario;
import com.osvaldoprosper.curso.security.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public Usuario findByEmailQuery(String email) {
		return repository.findByEmailQuery(email);
	}
	
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = findByEmailQuery(username);
		return new User(
				user.getEmail(),
				user.getSenha(),
				AuthorityUtils.createAuthorityList(getAuthorities(user.getPerfis()))
				);
	}

	private String[] getAuthorities(List<Perfil> perfis) {
		String[] authorities = new String[perfis.size()];
		for(int i=0; i<perfis.size();i++) {
			authorities[i] = perfis.get(i).getDesc();
		}
		return authorities;
	}

}
