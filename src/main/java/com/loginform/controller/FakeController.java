package com.loginform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
		User dev = userRepo.save(new User("1", "1", "Dev", "01/01/2000", "Male", "Av. Everywhere,0000"));
		Task task1 = taskRepo.save(new Task("Task1", "01/01/2001", "02/01/2001", dev));
		Task task2 = taskRepo.save(new Task("Task2", "01/02/2002", "02/02/2002", dev));
		dev.getTasks().add(task1);
		dev.getTasks().add(task2);
		return "redirect:/";
		
	}

}
