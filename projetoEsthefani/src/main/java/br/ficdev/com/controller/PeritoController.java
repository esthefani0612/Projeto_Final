package br.ficdev.com.controller;

import java.security.Principal;
import java.util.Collections;
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

import br.ficdev.com.model.HoraExtra;
import br.ficdev.com.model.Perito;
import br.ficdev.com.repository.HoraExtraRepositoty;
import br.ficdev.com.repository.PeritoRepository;
import jakarta.validation.Valid;;

@Controller
@RequestMapping("/perito")
public class PeritoController {
	@Autowired
	HoraExtraRepositoty horaExtraRepo;
	
    @Autowired
    PeritoRepository peritoRepo;


    // Listar os dados do perito 
    @GetMapping("/dashbord-perito")
    public ModelAndView listaPerito(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("dashbord-perito");

        if (principal != null) {
            // Recuperar o CPF do perito autenticado
            String cpfPeritoAutenticado = principal.getName();
            // Buscando o perito autenticado pelo CPF
            Perito peritoAutenticado = peritoRepo.findById(cpfPeritoAutenticado).orElse(null);

            if (peritoAutenticado != null) {
                // Se o perito autenticado existe, coloque-o na lista
                modelAndView.addObject("perito", Collections.singletonList(peritoAutenticado));

                // Buscando as HorasExtras associadas ao perito autenticado
                List<HoraExtra> horas = horaExtraRepo.findByPerito(peritoAutenticado);

                modelAndView.addObject("horasExtra", horas);
            } else {
                // Caso contrário, essa mensagem de erro aparecerá.
                modelAndView.addObject("mensagem", "Perito não encontrado.");}
        } else {
            // Caso o principal seja nulo (usuário não autenticado), será redirecionado para a página de login
            modelAndView.setViewName("redirect:/login");}

        return modelAndView;
    }
	
    
	
	@GetMapping("/apagar/{id}")
	public String deletarPerito(@PathVariable("id") String cpf) {
		peritoRepo.deleteById(cpf);
		return "redirect:/coordenador/listar-perito";
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
	        if (result.hasFieldErrors("cpf")) {
	            modelAndView.addObject("mensagem", "CPF inválido.");
	        }else if (result.hasFieldErrors("email")) {
	            modelAndView.addObject("mensagem", "email inválido.");
	        }
	        return modelAndView;
	    }

	    perito.setCpf(cpf);
	    peritoRepo.save(perito);
	    modelAndView.addObject("mensagemsalvar", "Atualizado com sucesso!");
	    return modelAndView;
	}
    
}
