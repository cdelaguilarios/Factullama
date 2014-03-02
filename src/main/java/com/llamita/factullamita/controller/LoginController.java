package com.llamita.factullamita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fabiosalasm on 01/03/14.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String error, ModelMap modelMap) {

        if (error != null && "true".equals(error)) {
            modelMap.addAttribute("error", true);
        }

        return "login";
    }

}
