package com.loginform.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.loginform.bean.User;
import com.loginform.dao.TaskRepository;
import com.loginform.dao.UserRepository;

@Controller
@RequestMapping("/user")
@SessionAttributes("loggedUser")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TaskRepository taskRepo;

	@GetMapping("/user-home-action")
	public String loginRequest(HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		user.getTasks().clear();
		user.getTasks().addAll(taskRepo.findByUserId(user.getId()));
		return "views/user_home_page";
	}

	@GetMapping("/register-action")
	public String registerPage() {
		return "views/register_page";
	}

	@PostMapping("/register-action")
	public String registerRequest(@RequestParam("login") String login, @RequestParam("password") String password, @RequestParam("name") String name, Model model) {
		String msg = "Registered user successfully";
		model.addAttribute("msg", msg);
		if (userRepo.findOneUserByLogin(login) != null) {
			msg = "This Login is already been used";
			model.addAttribute("msg", msg);
			return "views/register_page";
		} else {
			userRepo.save(new User(login, password, name));
			return "views/home_page";
		}

	}

	@GetMapping("/edit-profile-request-action")
	public String editProfilePage(Model model, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		loggedUser = userRepo.findOne(((User)session.getAttribute("loggedUser")).getId());
		model.addAttribute("loggedUser", loggedUser);
		return "views/edit_profile_page";
	}

	@PostMapping("/user-edit-request-action")
	public String userEditRequest(@RequestParam("name") String name, @RequestParam("dateOfBirth")@DateTimeFormat (pattern="yyyy-MM-dd") Date dateOfBirth,
			@RequestParam("gender") String gender, @RequestParam("adress") String adress, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");								
		loggedUser.setName(name);
		loggedUser.setDateOfBirth(dateOfBirth);
		loggedUser.setGender(gender);
		loggedUser.setAdress(adress);
		userRepo.save(loggedUser);
		return "redirect:/user/user-home-action";

	}
		
}
