package com.llamita.factullamita.controller;

import com.llamita.factullamita.view.AdminPwdBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fabiosalasm on 16/03/14.
 */
@Controller
public class ProfileController {

    @RequestMapping(value = "/admin/cambiar-pwd", method = RequestMethod.GET)
    public String cambiarPwdInit(ModelMap modelMap) {
        AdminPwdBean admin = new AdminPwdBean();
        modelMap.addAttribute("admin", admin);

        return "/profile/cambiar-pwd";
    }
}
