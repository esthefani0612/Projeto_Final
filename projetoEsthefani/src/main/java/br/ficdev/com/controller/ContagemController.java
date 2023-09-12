package br.ficdev.com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ficdev.com.ContagemDePeritos;

@Controller
@RequestMapping("/peritos")
public class ContagemController {
	
	public class ContagemDePeritosController {

	    @GetMapping("/count")
	    public Map<String, Integer> getCountPeritos() {
	        int totalPeritos = ContagemDePeritos.countPeritos();
	        Map<String, Integer> result = new HashMap<>();
	        result.put("totalPeritos", totalPeritos);
	        return result;
	    }
	}
}
