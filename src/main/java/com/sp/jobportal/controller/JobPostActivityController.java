package com.sp.jobportal.controller;

import com.sp.jobportal.services.UsersService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobPostActivityController {

    private final UsersService usersService;

    public JobPostActivityController(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping("/dashboard")
    public String searchJobs(Model model){

        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)){
            String currentUserName = auth.getName();
            model.addAttribute("currentUserName", currentUserName);
        }
        model.addAttribute("user", currentUserProfile);

        return "dashboard";
    }

}
