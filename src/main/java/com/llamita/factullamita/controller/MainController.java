package com.llamita.factullamita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/admin")
public class MainController {

	@RequestMapping(value="/index", method= RequestMethod.GET)
    public String dashboard(ModelMap modelMap) {

        return "index";
    }
	
}
