package br.ficdev.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ficdev.com.model.Coordenador;
import br.ficdev.com.model.Perito;
import br.ficdev.com.repository.CoordenadorRepository;
import br.ficdev.com.repository.PeritoRepository;

@Controller
public class LoginController {

	@Autowired
	PeritoRepository peritoRepo;
	@Autowired
	CoordenadorRepository coordenadorRepo;
	
	@GetMapping("/login")
	public String index(){
		return "login";
		}
	
	@PostMapping("/logar")
    public String logar(Model model, @RequestParam("username") String username, @RequestParam("senha") String senha) {
        try {
            Coordenador coordenador = coordenadorRepo.login(username, senha);
            if (coordenador != null) {
                // Autenticação bem-sucedida para o Coordenador
                return "redirect:/coordenador";
            }

            Perito perito = peritoRepo.loginP(username, senha);
            if (perito != null) {
                // Autenticação bem-sucedida para o Perito
                return "redirect:/perito/dashbord-perito";
            }

            throw new Exception();
        } catch (Exception e) {
            model.addAttribute("erro", "Usuário ou senha inválidos!");
            return "login";
        }
    }
}


