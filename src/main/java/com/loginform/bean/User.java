package com.loginform.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Proxy(lazy = false) // verificar necessidade
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	@Column(unique =true)
	private String login;
	private String password;
	private String name;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="dd-MM-YYY")
	private Date dateOfBirth;
	private String gender;
	private String adress;
	@OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private List<Task> tasks;

	public User() {
		this.tasks = new ArrayList<Task>();
	}

	public User(String login, String password, String name) {
		this();
		this.login = login;
		this.password = password;	
		this.name = name;
	}
	
	public User(String login, String password, String name, Date dateOfBirth, String gender, String adress) {
		this(login,password,name);
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.adress = adress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
