package br.ficdev.com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ficdev.com.model.EscalaTrabalho;
import br.ficdev.com.model.HoraExtra;
import br.ficdev.com.repository.HoraExtraRepositoty;
import jakarta.validation.Valid;

@Controller
//Quando o usuário clicar em "Solicitar Hora Extra, ele será redirecionado para esta URL abaixo.
@RequestMapping("/dashbord-perito/solicitacao")
public class HoraExtraController {

	@Autowired
	HoraExtraRepositoty horaExtraRepo;
	
	@GetMapping
	public String mostraForm(HoraExtra horaExtra) {
		return "criar-horaextra";
	}
	
	
	@PostMapping("/criar-hora-extra")
	public ModelAndView solicitacaoHoraExtra(@Valid @ModelAttribute("horaExtra") HoraExtra hora, BindingResult result) {
		ModelAndView model = new ModelAndView("criar-horaextra");
		
		if(result.hasErrors()) {
			if(result.hasFieldErrors("hora_total")) {
				model.addObject("mensagem", "Carga horária total excedida!");
			}
			return model;}
		
		horaExtraRepo.saveAndFlush(hora);
		model.addObject("mensagemsalvar","Solicitação enviada.");
		return model;
	}
	
	@GetMapping("/listar-horaextra")
	public ModelAndView listaHoraextra() {
		ModelAndView modelAndView = new ModelAndView("listar-horaextra");
		List<HoraExtra> hora = horaExtraRepo.findAll();
		modelAndView.addObject("hora", hora);
		return modelAndView;
	}
	
	
	@GetMapping("/apagar/{id}")
	public String deletarHoraExtra(@PathVariable("id") Long id) {
		horaExtraRepo.deleteById(id);
		return "redirect:/dashbord-perito/solicitacao/listar-horaextra";
	}
	
	
//	@GetMapping("listar-horaExtra")
//	public ModelAndView listarHorasExtra() {
//		ModelAndView modelAndView = new ModelAndView("listar-horas");
//		List<HoraExtra> horas = horaExtraRepo.findAll();
//		modelAndView.addObject("horasExtra", horas);
//		return modelAndView;
//	}
	
}
