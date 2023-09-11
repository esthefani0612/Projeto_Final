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
import br.ficdev.com.repository.HoraExtraRepositoty;
import br.ficdev.com.repository.PeritoRepository;
import jakarta.validation.Valid;;

@Controller
@RequestMapping
public class PeritoController {

	@Autowired
	HoraExtraRepositoty horaExtraRepo;
	
    @Autowired
    PeritoRepository peritoRepo;

//    @GetMapping
//    public String mostrarLogin(Perito perito){
//        return "tela-login";    }

    // Listar os dados do perito 
    @GetMapping("/dashbord")
    public ModelAndView listaPerito() {
        ModelAndView modelAndView = new ModelAndView("listar");
        List<Perito> peritos = peritoRepo.findAll();
        modelAndView.addObject("perito", peritos);
        return modelAndView;
    }



    //Atualizar os dados do perito
    @GetMapping("/atualizar/{cpf}")
	public String updatePerito(@PathVariable String cpf, Model model) {
		Perito perito = peritoRepo.findById(cpf).orElse(null);
		model.addAttribute("peritos", perito);
		return "atualizar";
	}
    @PostMapping("/atualizar/{cpf}")
	public ModelAndView atualizarPerito(@PathVariable("cpf") String cpf, @Valid @ModelAttribute("perito") Perito perito, BindingResult result) {
	    ModelAndView modelAndView = new ModelAndView("atualizar");
	    modelAndView.addObject("peritos", perito);

	    if (result.hasErrors()) {
	        if (result.hasFieldErrors("nome")) {
	            modelAndView.addObject("mensagem", "O nome deve conter no mínimo 2 caracteres");
	        } else if (result.hasFieldErrors("telefone")) {
	            modelAndView.addObject("mensagem", "O UUID deve ser maior ou igual a 2");
	        }else if (result.hasFieldErrors("email")) {
	            modelAndView.addObject("mensagem", "O padrão de cpf é inválido");
	        }
	        return modelAndView;
	    }

	    perito.setCpf(cpf);
	    peritoRepo.save(perito);
	    modelAndView.addObject("mensagemsalvar", "Atualizado com sucesso!");
	    return modelAndView;
	}
    
}
