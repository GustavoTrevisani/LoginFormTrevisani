package com.loginform.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loginform.bean.Task;
import com.loginform.bean.User;
import com.loginform.dao.TaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskRepository taskRepo;

	@GetMapping("/create-task-request-action")
	public String createTaskPage(Model model, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		model.addAttribute("loggedUser", loggedUser);
		return "views/create_task_page";
	}

	@PostMapping("/create-task-request")
	public String createTask(@RequestParam("name") String name, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
			Model model, RedirectAttributes redAtri, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		loggedUser.getTasks().add(taskRepo.save(new Task(name, beginDate, endDate, loggedUser)));
		return "redirect:/user/user-home-action";
	}
}
