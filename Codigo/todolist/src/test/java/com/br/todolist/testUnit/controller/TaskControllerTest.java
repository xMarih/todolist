package com.br.todolist.testUnit.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.br.todolist.entity.Task;
import com.br.todolist.mock.TaskMock;
import com.br.todolist.repository.TaskRepository;
import com.br.todolist.service.TaskService;


@ExtendWith(MockitoExtension.class)
@ExtendWith({ SpringExtension.class, MockitoExtension.class })
public class TaskControllerTest {
	@Mock
	TaskRepository tasksRepository;
	private TaskService taskService;

@BeforeEach
public void setup() {
taskService = new TaskService(tasksRepository);
Pageable pageable = PageRequest.of(0, 5, Sort.by(
Sort.Order.asc("name"),
Sort.Order.desc("id")));
Mockito.lenient().when(tasksRepository.findAll(pageable)).thenReturn(TaskMock.createTasks());
}

	@Test
	@DisplayName("Should retun all tasks")
	public void should_list_all_tasks_repository() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name"), Sort.Order.desc("id")));
		Page<Task> tasks = taskService.findAllTask(pageable);
		assertEquals(tasks.getTotalPages(), 1);
		assertEquals(tasks.getNumberOfElements(), 2);
		assertNotNull(tasks);
	}
}
