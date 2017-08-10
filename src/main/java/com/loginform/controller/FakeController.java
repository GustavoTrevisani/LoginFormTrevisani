package com.loginform.controller;

import java.time.Instant;
import java.util.Date;

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
		Date data = new Date();
		data = Date.from(Instant.now());		
		User dev = userRepo.save(new User("1", "1", "Dev", data, "Male", "Av. Everywhere,0000"));
		Task task1 = taskRepo.save(new Task("Task1", data, data, dev));
		Task task2 = taskRepo.save(new Task("Task2", data, data , dev));
		dev.getTasks().add(task1);
		dev.getTasks().add(task2);
		return "redirect:/";
		
	}

}
