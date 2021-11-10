package com.gft.starter.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.starter.api.entities.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@GetMapping("usuario")
	public ResponseEntity<String> metodoGetUsuario(){
		
		Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(usuario);
		
		if(usuario.getPerfil().getNome().equals("ADMIN")) {
			return ResponseEntity.ok("Usuário é um Admin");
		}
		
		return ResponseEntity.ok("Usário é um Starter");
	}
}
