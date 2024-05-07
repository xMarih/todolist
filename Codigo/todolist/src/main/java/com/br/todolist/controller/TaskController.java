package com.br.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.todolist.entity.Task;
import com.br.todolist.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RequestMapping("/task")
@RestController
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@Operation(summary = "Lista todas as tarefas com data")
	@GetMapping(path = "/getTasksWithDoDate")
	private ResponseEntity<Object> getTasksWithDoDate() {
		try {
			return new ResponseEntity<Object>(taskService.findTasksWithDoDate(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Lista todas as tarefas com prazo")
	@GetMapping(path = "/getTasksWtihDeadLine")
	private ResponseEntity<Object> getTasksWtihDeadLine() {
		try {
			return new ResponseEntity<Object>(taskService.findTasksWtihDeadLine(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Lista todas as tarefas Livre")
	@GetMapping(path = "/getAllFreeTasks")
	private ResponseEntity<Object> getAllFreeTasks() {
		try {
			return new ResponseEntity<Object>(taskService.findAllFreeTasks(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@Operation(summary = "Cria varias tarefa com data")
//	@PostMapping(path = "/createMultipleTaskWtihDeadLine")
//	private ResponseEntity<Object> createMultipleTaskWtihDeadLine(List<Task> task) {
//		try {
//			return new ResponseEntity<Object>(taskService.createMultipleTaskWtihDeadLine(task), HttpStatus.CREATED);
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@Operation(summary = "Cria varias tarefa com data")
//	@PostMapping(path = "/createMultipleTaskWtihDoDate")
//	private ResponseEntity<Object> createMultipleTaskWtihDoDate(List<Task> task) {
//		try {
//			return new ResponseEntity<Object>(taskService.createMultipleTaskWtihDoDate(task), HttpStatus.CREATED);
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	
	@Operation(summary = "Cria tarefa com data")
	@PostMapping(path = "/createTaskWithDoDate")
	private ResponseEntity<Object> createTaskWithDoDate(Task task) {
		try {
			return new ResponseEntity<Object>(taskService.createTaskWithDoDate(task), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Cria tarefa com prazo")

	@PostMapping(path = "/createTaskWtihDeadLine")
	private ResponseEntity<Object> createTaskWtihDeadLine(Task task) {
		try {
			return new ResponseEntity<Object>(taskService.createTaskWtihDeadLine(task), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Operation(summary = "Cria tarefa")
	@PostMapping(path = "/createTask")
	private ResponseEntity<Object> createTask(Task task) {
		try {
			return new ResponseEntity<Object>(taskService.createTask(task), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Lista todas as tarefas")
	@GetMapping(path = "/getAllTask")
	private ResponseEntity<Object> getAllTask() {
		try {
			return new ResponseEntity<Object>(taskService.findAllTask(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Busca tarefa por id")
	@GetMapping(path = "/getTaskById")
	private ResponseEntity<Object> getTask(Long id) {
		try {
			return new ResponseEntity<Object>(taskService.findTask(id), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Atualiza tarefa por id")
	@PutMapping(path = "/updateTask")
	private ResponseEntity<Object> updateTask(Task task) {
		try {
			return new ResponseEntity<Object>(taskService.updateTask(task), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Delete tarefa por id")
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
