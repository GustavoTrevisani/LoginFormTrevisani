package com.loginform.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loginform.bean.User;
import com.loginform.dao.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/")
	public String homePage(HttpSession session) {
		if(session.getAttribute("loggedUser")!= null) {
			return "redirect:/user/user-home-action";
		}
		return "views/home_page";
	}
	
	@GetMapping("/logout-action")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}

	@PostMapping("/login-action")
	public String loginRequest(@RequestParam("login") String login, @RequestParam("password") String password, HttpSession session, Model model) {
		User user = userRepo.findOneUserByLoginAndPassword(login, password);
		if (user != null) {
			session.setAttribute("loggedUser", user);
			return "redirect:/user/user-home-action";
		} else {
			model.addAttribute("msg","Login or Password doesn't match ou doesn't exist" );
			model.addAttribute("msgType","errorMsg");			
			return "views/home_page";

		}

	}

}
