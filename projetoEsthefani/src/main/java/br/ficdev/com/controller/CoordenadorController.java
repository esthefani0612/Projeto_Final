package br.ficdev.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.ficdev.com.model.Coordenador;
import br.ficdev.com.model.Perito;
import br.ficdev.com.repository.CoordenadorRepository;
import br.ficdev.com.repository.PeritoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/coordenador")
public class CoordenadorController {

	@Autowired
	CoordenadorRepository coordenadorRepo;
	@Autowired
	PeritoRepository peritoRepo;
	
	@GetMapping
	public String mostraForm(Perito perito) {
		return "dashbord-coordenador";
	}
	
	
	@PostMapping("/criar")
	public ModelAndView cadastrarPeritos(@Valid @ModelAttribute("perito") Perito perito, BindingResult result) {
		
		ModelAndView modelAndView = new ModelAndView("dashbord-coordenador");
		if(result.hasErrors()) {
			if(result.hasFieldErrors("cpf")) {
				modelAndView.addObject("mensagem", "CPF inválido.");
		}else if(result.hasFieldErrors("username")) {
			modelAndView.addObject("mensagem", "Este campo não pode estar vazio");
		}else if(result.hasFieldErrors("senha")) {
			modelAndView.addObject("mensagem", "Este campo não pode estar vazio");
		}else if(result.hasFieldErrors("email")) {
			modelAndView.addObject("mensagem","email inválido");
		}else if(result.hasFieldErrors("telefone")) {
			modelAndView.addObject("mensagem","Número de celular inválido");
		}
			return modelAndView;
		}
	
		peritoRepo.saveAndFlush(perito);
		modelAndView.addObject("mensagemsalvar", "Perito cadastrado no sistema.");
	
		return modelAndView;
	}
	
	
	@GetMapping("/listar")
	public ModelAndView listarCoordenador() {
		ModelAndView modelAndView = new ModelAndView("listar-coordenador");
		List<Coordenador> coordena = coordenadorRepo.findAll();
		modelAndView.addObject("coordenador", coordena);
		return modelAndView;
	}
	
	
	@GetMapping("/listar-perito")
	public ModelAndView listarTodosPeritos() {
		ModelAndView modelAndView = new ModelAndView("listar-peritos");
		List<Perito> perito = peritoRepo.findAll();
		modelAndView.addObject("perito", perito);
		return modelAndView;
	}
	
	
	@GetMapping("/atualizar/{id}")
	public String updateMerendeira(@PathVariable Long id, Model model) {
		Coordenador coordenador = coordenadorRepo.findById(id).orElse(null);
		model.addAttribute("coordenadores", coordenador);
		return "atualizar";
	}
	@PostMapping("/atualizar/{id}")
	public ModelAndView atualizarCoordenador(@PathVariable("id") Long id, @ModelAttribute("coordenadores") Coordenador coordenador) {
	    ModelAndView modelAndView = new ModelAndView("atualizar");
	    modelAndView.addObject("coordenadores", coordenador);

	    coordenador.setId(id);
	    coordenadorRepo.save(coordenador);
	    modelAndView.addObject("mensagemsalvar", "Merendeira atualizada com sucesso!");
	    
	    return modelAndView;}
	
	
		
}
	

