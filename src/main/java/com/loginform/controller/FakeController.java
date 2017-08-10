package com.loginform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginform.bean.Task;
import com.loginform.bean.User;
import com.loginform.dao.TaskRepository;
import com.loginform.dao.UserRepository;

@Controller
public class FakeController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TaskRepository taskRepo;
	
	@GetMapping("/database")
	public String createFakeDatabase() {
		User user = userRepo.save(new User("asd", "asd", "nomeasd", "datanaschimento", "bixa", "loladeoliveira"));
		Task task1 = taskRepo.save(new Task("Task1", "10/04/17", "10/04/18", user));
		Task task2 = taskRepo.save(new Task("Task2", "11/04/17", "11/04/18", user));
		user.getTasks().add(task1);
		user.getTasks().add(task2);
		return "redirect:/";
		
	}

}
