package br.ficdev.com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import br.ficdev.com.model.HoraExtra;
import br.ficdev.com.repository.HoraExtraRepositoty;

@Controller
//Quando o usuário clicar em "Solicitar Hora Extra, ele será redirecionado para esta URL abaixo.
@RequestMapping("/dashbord-perito/solicitacao")
public class HoraExtraController {

	@Autowired
	HoraExtraRepositoty horaExtraRepo;
	
	@GetMapping
	public String mostraForm(HoraExtra horaExtra) {
		return "solicitar-hora-extra";
	}
	
	
	@PostMapping("/criar-hora-extra")
	public ModelAndView solicitacaoHoraExtra(@ModelAttribute("horasExtras") HoraExtra hora) {
		ModelAndView model = new ModelAndView("solicitacao-HoraExtra");
		horaExtraRepo.save(hora);
		model.addObject("mensagemsalvar","Solicitação enviada.");
		return model;
	}
	
	
	
	@GetMapping("/apagar/{id}")
	public String deletarHoraExtra(@PathVariable("id") Long id) {
		horaExtraRepo.deleteById(id);
		return "redirect:/dashbord-perito";
	}
	
	
//	@GetMapping("listar-horaExtra")
//	public ModelAndView listarHorasExtra() {
//		ModelAndView modelAndView = new ModelAndView("listar-horas");
//		List<HoraExtra> horas = horaExtraRepo.findAll();
//		modelAndView.addObject("horasExtra", horas);
//		return modelAndView;
//	}
	
}
