package com.llamita.factullamita.controller;

import com.llamita.factullamita.logic.AdminLogic;
import com.llamita.factullamita.view.AdminPwdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class ProfileController {

    @Autowired
    private AdminLogic adminLogic;

    @RequestMapping(value = "/admin/cambiar-pwd", method = RequestMethod.GET)
    public String cambiarPwdInit(ModelMap modelMap) {
        AdminPwdBean admin = new AdminPwdBean();

        if(false == modelMap.containsAttribute("admin")) {
            modelMap.addAttribute("admin", admin);
        }

        return "/profile/cambiar-pwd";
    }

    @RequestMapping(value = "/admin/cambiar-pwd", method = RequestMethod.POST)
    public String cambiarPwd(@Valid @ModelAttribute(value="admin") AdminPwdBean admin, final BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.admin", bindingResult)
                              .addFlashAttribute("admin", admin);

            return "redirect:/admin/cambiar-pwd";
        }

        adminLogic.updatePassword(admin.getPassword());
        return "redirect:/logout";
    }
}
