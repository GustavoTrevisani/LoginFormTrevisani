package com.loginform.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.loginform.bean.User;
import com.loginform.dao.TaskRepository;
import com.loginform.dao.UserRepository;

@Controller
@RequestMapping("/user")
@SessionAttributes("loggedUser")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/user-home-action")
	public String loginRequest() {
		return "views/user_home_page";
	}

	@GetMapping("/register-action")
	public String registerPage() {
		return "views/register_page";
	}

	@PostMapping("/register-action")
	public String registerRequest(@RequestParam("login") String login, @RequestParam("password") String password, Model model,
			RedirectAttributes redAtri) {
		String msg = "Registered user successfully";
		model.addAttribute("msg", msg);
		if (userRepo.findOneUserByLogin(login) != null) {
			msg = "This Login is already been used";
			model.addAttribute("msg", msg);
			return "views/register_page";
		} else {
			userRepo.save(new User(login, password));
			return "views/home_page";
		}

	}

	@GetMapping("/edit-profile-request-action")
	public String editProfilePage(Model model, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		model.addAttribute("loggedUser", loggedUser);
		return "views/edit_profile_page";
	}

	@PostMapping("/user-edit-request-action")
	public String userEditRequest(@RequestParam("name") String name, @RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("gender") String gender, @RequestParam("adress") String adress, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		loggedUser.setName(name);
		loggedUser.setDateOfBirth(dateOfBirth);
		loggedUser.setGender(gender);
		loggedUser.setAdress(adress);
		userRepo.save(loggedUser);
		return "redirect:/user/login-action";

	}
}
