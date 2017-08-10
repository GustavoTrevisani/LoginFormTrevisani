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
	@GetMapping("/edit-task-request-action")
	public String editTaskPage(@RequestParam("id") String id,HttpSession session, Model model) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		Long taskId = Long.parseLong(id);
		Task task = taskRepo.findOne(taskId);
		String beginDate = task.getBeginDate().toString();
		String endDate = task.getEndDate().toString();
		model.addAttribute("id", id);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("task", task);
		return "views/edit_task_page";
	}
	@PostMapping("/edit-task-request-action")
	public String editTaskRequest(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate, HttpSession session, Model model) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		Long taskId = Long.parseLong(id);
		Task task = taskRepo.findOne(taskId);			
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
		task.setBeginDate(dateOfBegin);
		task.setEndDate(dateOfEnd);
		task.setName(name);
		taskRepo.save(task);
		loggedUser.getTasks().clear();
		loggedUser.getTasks().addAll(taskRepo.findByUserId(loggedUser.getId()));		
		return "redirect:/user/user-home-action";
	}

	@PostMapping("/create-task-request-action")
	public String createTask(@RequestParam("name") String name, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate,
			Model model, HttpSession session) {
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
				return "redirect:/user/user-home-action";
	}
}
