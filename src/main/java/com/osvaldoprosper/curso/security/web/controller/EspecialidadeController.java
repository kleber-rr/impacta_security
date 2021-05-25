package com.osvaldoprosper.curso.security.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.osvaldoprosper.curso.security.domain.Especialidade;
import com.osvaldoprosper.curso.security.service.EspecialidadeService;

@Controller
@RequestMapping("especialidades")
public class EspecialidadeController {
	
	@Autowired
	private EspecialidadeService service;
	
		@GetMapping("/editar/{id}")
		public String preEditar(@PathVariable("id") Long id, ModelMap model) {
			model.addAttribute("especialidade", service.findById(id));
			return "especialidade/especialidade";
		}
		
		@GetMapping("/excluir/{id}")
		public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
			try {
				service.delete(id);
				attributes.addFlashAttribute("sucesso", "Operação realizada com sucesso!!");
			} catch (Exception e) {
				attributes.addFlashAttribute("falha", "Deu erro na Operação...");
			}
			return "redirect:/especialidades";
		}
	
		// abrir cadastro de usuarios (medico/admin/paciente)
		@GetMapping({"","/"})
		public String abrir(Especialidade especialidade) {
			return "especialidade/especialidade";
		}
		
		@PostMapping("/salvar")
		private String salvar(Especialidade especialidade, RedirectAttributes attributes) {
			try {
				service.salvar(especialidade);
				attributes.addFlashAttribute("sucesso", "Operação realizada com sucesso!!");
			} catch (Exception e) {
				attributes.addFlashAttribute("falha", "Deu erro na Operação...");
			}
			return "redirect:/especialidades";
		}
		
		@GetMapping({"/datatables/server"})
		public ResponseEntity<?> getEspecialidades(HttpServletRequest request) {
			return ResponseEntity.ok(service.buscarEspecialidades(request));
		}
}
