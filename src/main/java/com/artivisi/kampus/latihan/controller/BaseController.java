package com.artivisi.kampus.latihan.controller;

import com.artivisi.kampus.latihan.dao.UserProfileDao;
import com.artivisi.kampus.latihan.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice(basePackages = {"com.artivisi.kampus.latihan.controller"})
public class BaseController {

    @Autowired private UserProfileDao userProfileDao;

    @ModelAttribute
    public void simpanUser(Principal principal, ModelMap modelMap){

        if(principal != null){
            UserProfile profile = userProfileDao.findByUserUsername(principal.getName());
            modelMap.addAttribute("currentUser",profile);
        }
    }

}
