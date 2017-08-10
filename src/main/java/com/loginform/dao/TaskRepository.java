package com.loginform.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.loginform.bean.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	public List<Task> findByUserId(Long userId);

}
