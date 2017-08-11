package com.loginform.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task {	
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="YYYY-MM-dd")
	private Date beginDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="YYYY-MM-dd")
	private Date endDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Task() {
	}
	
	public Task(String name, Date beginDate, Date endDate, User user) {
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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
