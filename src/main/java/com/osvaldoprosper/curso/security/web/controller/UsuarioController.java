package com.osvaldoprosper.curso.security.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.osvaldoprosper.curso.security.domain.Usuario;
import com.osvaldoprosper.curso.security.service.UsuarioService;

@Controller
@RequestMapping("u")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/cadastro/salvar")
	private String salvar(Usuario usuario, RedirectAttributes attributes) {
		try {
			service.salvar(usuario);
			attributes.addFlashAttribute("sucesso", "Operação realizada com sucesso!!");
		} catch (Exception e) {
			attributes.addFlashAttribute("falha", "Deu erro na Operação...");
		}
		return "redirect:/u";
	}

	// abrir cadastro de usuarios (medico/admin/paciente)
	@GetMapping("/novo/cadastro/usuario")
	public String cadastroPorAdminParaAdminMedicoPaciente(Usuario usuario) {
		return "usuario/cadastro";
	}

	// abrir lista de usuarios
	@GetMapping("/lista")
	public String listarUsuarios() {
		return "usuario/lista";
	}
	
	@GetMapping({"/datatables/server/usuarios"})
	public ResponseEntity<?> getUsuarios(HttpServletRequest request) {
		return ResponseEntity.ok(service.buscarUsuarios(request));
	}

}
