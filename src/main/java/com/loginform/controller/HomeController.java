package com.loginform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.loginform.bean.User;
import com.loginform.dao.UserRepository;

@Controller
@SessionAttributes("loggedUser")
public class HomeController {

	@Autowired
	UserRepository userRepo;

	@GetMapping("/")
	public String homePage(Model model) {
		return "views/home_page";
	}

	@PostMapping("/login-action")
	public String loginRequest(@RequestParam("login") String login, @RequestParam("password") String password,
			Model model) {
		String msgType = "errorMsg";
		String msg = "Welcome " + login;
		if (userRepo.findOneUserByLoginAndPassword(login, password) != null) {
			User user = userRepo.findOneUserByLogin(login);
			model.addAttribute("msg", msg);
			model.addAttribute("loggedUser", user);
			return "views/user_home_page";
		} else {			
			msg = "Login or Password doesn't match ou doesn't exist";
			model.addAttribute("msgType", msgType);
			model.addAttribute("msg", msg);			
			return "views/home_page";
		}

	}

}
