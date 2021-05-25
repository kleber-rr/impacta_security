package com.osvaldoprosper.curso.security.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osvaldoprosper.curso.security.datatables.Datatables;
import com.osvaldoprosper.curso.security.datatables.DatatablesColunas;
import com.osvaldoprosper.curso.security.domain.Especialidade;
import com.osvaldoprosper.curso.security.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	@Autowired
	private EspecialidadeRepository repository;
	
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = false)
	public void salvar(Especialidade especialidade) {
		repository.save(especialidade);
	}

	@Transactional(readOnly = true)
	public List<Especialidade> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> buscarEspecialidades(HttpServletRequest request){
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.ESPECIALIDADES);
		Page<?> page = datatables.getSearch().isEmpty()
				? repository.findAll(datatables.getPageable()) 
//				: repository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
				: repository.findByTituloContaining(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
		
	}

	@Transactional(readOnly = true)
	public Object findById(Long id) {
		Optional<Especialidade> espec = repository.findById(id);
		return espec.get();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
