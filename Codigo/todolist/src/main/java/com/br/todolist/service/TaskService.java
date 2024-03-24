package com.br.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.todolist.entity.Task;
import com.br.todolist.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	public Task createTask(Task novaTask) {
		return taskRepository.save(novaTask);
	}
	
	
	public List<Task> findAllTask() {
		
		return taskRepository.findAll();
	}


	public Task findTask(Long id) {
		return taskRepository.findById(id).orElse(null);
	}
	
	
	public Task updateTask(Task task) {
		
		Task task1 = taskRepository.findById(task.getId()).orElse(null);
		
		if(task1 != null ) {
			task1.setDescription(task.getDescription());
			task1.setDeleted(task.getDeleted());
			task1.setDone(task.getDone());
		}
		return taskRepository.save(task1);
	}
	
	
	public void deleteTask(Long id) {
		
		 taskRepository.deleteById(id);
	}
	
	
	
//	public Date retornaDataBanco() {
//		return taskRepository.dataBanco();
//		
//	}















	
}
