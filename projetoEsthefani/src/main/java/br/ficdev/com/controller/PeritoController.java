package br.ficdev.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ficdev.com.model.Perito;
import br.ficdev.com.repository.PeritoRepository;



@Controller
@RequestMapping("/")
public class PeritoController {

    @Autowired
    PeritoRepository peritoRepo;

    @GetMapping
    public String mostrarLogin(Perito perito){
        return "tela-login";    }

    @GetMapping("/entrar")
    public ModelAndView listaDashbord(){
        ModelAndView modelAndView =  new ModelAndView("dashbord");
        List<Perito> perito = peritoRepo.findAll();
        modelAndView.addObject("peritos", perito);
    
        return modelAndView;
    }


}
