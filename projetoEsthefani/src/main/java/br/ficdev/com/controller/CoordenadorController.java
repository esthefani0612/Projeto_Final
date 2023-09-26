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
import org.springframework.web.servlet.ModelAndView;

import br.ficdev.com.model.Coordenador;
import br.ficdev.com.model.EscalaTrabalho;
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
	
	@GetMapping("/listar-coordenador")
	public ModelAndView listaCoordenador() {
		ModelAndView modelAndView = new ModelAndView("listar-coordenador");
		List<Coordenador> coordenador = coordenadorRepo.findAll();
		modelAndView.addObject("coordenador", coordenador);
		return modelAndView;
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

	
	
	
	@GetMapping("/listar-perito")
	public ModelAndView listarTodosPeritos() {
		ModelAndView modelAndView = new ModelAndView("listar-peritos");
		List<Perito> perito = peritoRepo.findAll();
		modelAndView.addObject("perito", perito);
		return modelAndView;
	}
	
	
	//Atualizar os dados do coordenador
    @GetMapping("/atualizar/{cpf}")
	public String updateCoordenador(@PathVariable String cpf, Model model) {
		Coordenador coordenador = coordenadorRepo.findByCpf(cpf).orElse(null);
		model.addAttribute("coordenador", coordenador);
		return "atualizar-coordenador";
	}
	@PostMapping("/atualizar/{cpf}")
	public ModelAndView atualizarCoordenador(@Valid @PathVariable("cpf") String cpfCoordenador, @ModelAttribute("coordenador") Coordenador coordenador, BindingResult result) {
	    ModelAndView modelAndView = new ModelAndView("atualizar-coordenador");
	    modelAndView.addObject("coordenador", coordenador);

	    if (result.hasErrors()) {
	        if (result.hasFieldErrors("senha")) {
	            modelAndView.addObject("mensagem", "Este campo não pode estar em branco.");
	        }else if(result.hasFieldErrors("username")){
	            modelAndView.addObject("mensagem", "Este campo não pode estar em branco.");
	        }else if(result.hasFieldErrors("telefone"))
	            modelAndView.addObject("mensagem", "Número de celular inválido.");
	        }
	        
	    coordenador.setCpf(cpfCoordenador);
	    coordenadorRepo.save(coordenador);
	    modelAndView.addObject("mensagemsalvar", "Atualizado com sucesso!");
	    
	    return modelAndView;}
	
	
		
}
	

