package com.loginform.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.loginform.bean.Task;
import com.loginform.bean.User;
import com.loginform.dao.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	private TaskRepository taskRepo;	

	@GetMapping("/create-task-request")
	public String createTaskPage(Model model, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		System.out.println(loggedUser);
		model.addAttribute("loggedUser", loggedUser);
		return "views/create_task_page";
	}

	@PostMapping("/create-task-request")
	public String createTask(@RequestParam("name") String name, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
			Model model, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		taskRepo.save(new Task(name, beginDate, endDate, loggedUser));	
		List<Task> taskList = taskRepo.findByUserId(loggedUser.getId());				
		model.addAttribute("taskList", taskList);		
		model.addAttribute("loggedUser", loggedUser);
		return "views/user_home_page";
	}
}
