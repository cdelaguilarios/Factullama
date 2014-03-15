package com.llamita.factullamita.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

	private Logger log = Logger.getLogger(LoginController.class);
	
	@RequestMapping({"/"})
	public String initLogin(){
		log.info("* Iniciar Pagina principal *");
		return "login";
	}
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, ModelMap modelMap) {
    	log.info("* Validar Login [Inicio] *");
        if (error != null && "true".equals(error)) {
            modelMap.addAttribute("error", true);
        }
        log.info("* Validar Login [Fin] *");
        return "login";
    }

}
