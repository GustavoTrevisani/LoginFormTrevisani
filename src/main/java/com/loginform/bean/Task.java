package com.loginform.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {	
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String beginDate;
	private String endDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Task() {
	}
	
	public Task(String name, String beginDate, String endDate, User user) {
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
