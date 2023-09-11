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

import br.ficdev.com.model.Coordenador;
import br.ficdev.com.repository.CoordenadorRepository;

@Controller
@RequestMapping("/coordenador")
public class CoordenadorController {

	@Autowired
	CoordenadorRepository coordenadorRepo;
	
//	@GetMapping
//	public String mostraForm(Coordenador coordena) {
//		return "dashbord-coordenador";
//	}
	
	
	@GetMapping("/listar")
	public ModelAndView listarCoordenador() {
		ModelAndView modelAndView = new ModelAndView("listar-coordenador");
		List<Coordenador> coordena = coordenadorRepo.findAll();
		modelAndView.addObject("coordenador", coordena);
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
	

