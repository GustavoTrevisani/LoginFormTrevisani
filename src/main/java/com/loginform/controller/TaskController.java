package com.loginform.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	@PostMapping("/create-task-request-action")
	public String createTask(@RequestParam("name") String name, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
			Model model, RedirectAttributes redAtri, HttpSession session) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOfBegin = null;
		Date dateOfEnd = null;
		try {
			dateOfBegin = formatter.parse(beginDate);
			dateOfEnd = formatter.parse(endDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
		User loggedUser = (User) session.getAttribute("loggedUser");
		loggedUser.getTasks().add(taskRepo.save(new Task(name, dateOfBegin, dateOfEnd, loggedUser)));
		System.out.println(dateOfBegin.toString());
		return "redirect:/user/user-home-action";
	}
}
