package com.osvaldoprosper.curso.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.osvaldoprosper.curso.security.domain.Medico;

@Controller
@RequestMapping("medicos")
public class MedicoController {
	
	// abrir cadastro de usuarios (medico/admin/paciente)
		@GetMapping("/dados")
		public String abrirPorMedico(Medico medico, ModelMap model) {
			return "medico/cadastro";
		}	
}
