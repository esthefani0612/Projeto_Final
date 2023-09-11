package br.ficdev.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ficdev.com.model.Perito;
import br.ficdev.com.repository.CoordenadorRepository;
import br.ficdev.com.repository.PeritoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class CoordenadorController {

	@Autowired
	CoordenadorRepository coordenadorRepo;
	@Autowired
	PeritoRepository peritoRepo;
	
	
	@GetMapping("/listar")
	public ModelAndView listaTodosPeritos() {
		ModelAndView modelAndView = new ModelAndView("listar");
		List<Perito> peritos = peritoRepo.findAll();
		modelAndView.addObject("perito", peritos);
		return modelAndView;
	}
	
	
	@PostMapping("/dashbord")
	public ModelAndView cadastrarPeritos(@Valid @ModelAttribute("perito") Perito perito, BindingResult result) {
		
		ModelAndView modelAndView = new ModelAndView("cadastrar-perito");
		if(result.hasErrors()) {
			if(result.hasFieldErrors("cpf")) {
				modelAndView.addObject("mensagem", "CPF inválido.");
		}else if(result.hasFieldErrors("username")) {
			modelAndView.addObject("mensagem", "Este campo não pode estar vazio");
		}else if(result.hasFieldErrors("senha")) {
			modelAndView.addObject("mensagem", "Este campo não pode estar vazio");
		}else if(result.hasFieldErrors("email")) {
			modelAndView.addObject("mensagem","email inválido");
		}
			return modelAndView;
		}
	
		peritoRepo.save(perito);
		modelAndView.addObject("mensagemsalvar", "Perito cadastrado no sistema.");
	
		return modelAndView;
	}
	
	
	@GetMapping("/apagar/{id}")
	public String deletarPerito(@PathVariable("id") String cpf) {
		peritoRepo.deleteById(cpf);
		return "redirect:/listar";
	}
		
	}
	

