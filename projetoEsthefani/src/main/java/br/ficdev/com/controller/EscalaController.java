package br.ficdev.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import br.ficdev.com.model.EscalaTrabalho;
import br.ficdev.com.model.Perito;
import br.ficdev.com.repository.EscalaRepository;
@Controller
@RequestMapping("/coordenador/escala-trabalho")
public class EscalaController {
	
	@Autowired
	EscalaRepository escalaRepo;
	
	@GetMapping
	public String mostraForm(Model model) {
	    EscalaTrabalho escala = new EscalaTrabalho(); // Crie um novo objeto "escala"
	    model.addAttribute("escala", escala); // Adicione-o ao modelo
	    return "criar-escala";
	}
	
	
	@GetMapping("/listar-escalas")
	public ModelAndView listaEscalas() {
		ModelAndView modelAndView = new ModelAndView("listar-escala");
		List<EscalaTrabalho> escalas = escalaRepo.findAll();
		modelAndView.addObject("escalas", escalas);
		return modelAndView;
	}

	
	@PostMapping("/criar-escala")
	public ModelAndView criarEscaladeTrabalho(@ModelAttribute("escala") EscalaTrabalho escalaTrabalho) {
	    
	    ModelAndView modelAndView = new ModelAndView("criar-escala");
	  
	    escalaRepo.save(escalaTrabalho);
	    modelAndView.addObject("mensagemsalvar", "Escala criada com sucesso!");
	    return modelAndView;
	}
	
	
	@GetMapping("/apagar-escala/{id}")
	public String deletarEscala(@PathVariable("id") Long id) {
		escalaRepo.deleteById(id);
		return "redirect:/coordenador/escala-trabalho/listar-escalas";
	}
	
	//Métodos de atualização das escalas de trabalho
	@GetMapping("/atualizar-escala/{id}")
	public String updateEscala(@PathVariable Long id, Model model) {
		EscalaTrabalho escalaTrabalho = escalaRepo.findById(id).orElse(null);
		model.addAttribute("escalaTrabalho", escalaTrabalho);
		return "atualizar-escala";
	}
	
	@PostMapping("/atualizar-escala/{id}")
	public ModelAndView atualizarEscala(@PathVariable("id") Long id, @ModelAttribute("escalaTrabalho") EscalaTrabalho escala) {
	    ModelAndView modelAndView = new ModelAndView("atualizar-escala");
	    modelAndView.addObject("escalaTrabalho", escala);

	    escala.setId(id);
	    escalaRepo.save(escala);
	    modelAndView.addObject("mensagemsalvar", "Atualizado com sucesso!");
	    return modelAndView;
	}
	
	
}