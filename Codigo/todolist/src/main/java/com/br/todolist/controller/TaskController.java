package com.br.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.todolist.entity.Task;
import com.br.todolist.service.TaskService;

@CrossOrigin("*")
@RestController
@RequestMapping("/taks")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@PostMapping(path = "/createTask")
	private ResponseEntity<Object> createTask(Task task) {
		try {
			return new ResponseEntity<Object>(taskService.createTask(task), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@GetMapping(path = "/getAllTask")
	private ResponseEntity<Object> getAllTask() {
		try {
			return new ResponseEntity<Object>(taskService.findAllTask(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(path = "/getTask")
	private ResponseEntity<Object> getTask(Long id) {
		try {
			return new ResponseEntity<Object>(taskService.findTask(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PutMapping(path = "/updateTask")
	private ResponseEntity<Object> updateTask(Task task) {
		try {
			return new ResponseEntity<Object>(taskService.updateTask(task), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	@PatchMapping(path = "/updateSubTask")
//	private ResponseEntity<Object> updateSubTask(Task task) {
//		try {
//			return new ResponseEntity<Object>("ok", HttpStatus.OK);
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@DeleteMapping(path = "/deleteTask")
	private ResponseEntity<Object> deleteTask(Long id) {
		try {
			taskService.deleteTask(id);
			return new ResponseEntity<Object>("Task deleted", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
