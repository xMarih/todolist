package com.br.todolist.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.todolist.entity.Task;
import com.br.todolist.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	public TaskService(TaskRepository tasksRepository) {
		// TODO Auto-generated constructor stub
	}

	public List<Task> findAllFreeTasks() {
       return taskRepository.findAll().stream()
                .filter(task -> task.getDoDate() == null || 
                                task.getDeadLine() == null ||
                                task.getDone()) 
                .collect(Collectors.toList());
        
        
	}

	public List<Task> findTasksWtihDeadLine() {
		
		 List<Task> tasksWithDeadline = taskRepository.findAll().stream()
                 .filter(task -> task.getDeadLine() != null) 
                 .collect(Collectors.toList());
		 
		LocalDate dataAtual = LocalDate.now();
		for (Task task : tasksWithDeadline) {
			LocalDate dataInicioTarefa = task.getStartDate().toLocalDate();
			LocalDate dataTerminoEsperada = dataInicioTarefa.plusDays(task.getDeadLine());
			long diasParaTermino = ChronoUnit.DAYS.between(dataAtual, dataTerminoEsperada);
			
			if(diasParaTermino > 0){
				task.setStatus("O prazo para essa tarefa expira em: " + diasParaTermino + " dias");
			}
			else if(diasParaTermino == 0){
				task.setStatus("O prazo para essa tarefa expira hoje");
			}else if(diasParaTermino < 0){
				task.setStatus("Essa tarefa está atrasada em " + diasParaTermino + " dias");
			}
		}
		return tasksWithDeadline;
		
		
	}

	public List<Task> findTasksWithDoDate() {
		
		 List<Task> tasksWithDoDate = taskRepository.findAll().stream()
                 .filter(task -> task.getDoDate() != null) // Filtra onde deadline não é null
                 .collect(Collectors.toList());
		 
		 
		LocalDate dataAtual = LocalDate.now();

		for (Task task : tasksWithDoDate) {
			LocalDate dataInicioTarefa = task.getStartDate().toLocalDate();

			if (dataAtual.isAfter(dataInicioTarefa))
				task.setStatus(
						"Sua tarefa está " + 
				ChronoUnit.DAYS.between(dataInicioTarefa, dataAtual) + "dias em atraso");
		}
		return tasksWithDoDate;
	}

	public Task createTaskWithDoDate(Task task) throws Exception {
		if(task.getDoDate() == null) {
			throw new Exception ("Esse tipo de tarefa deve ter um tempo prazo");
		}
		if(task.getDeadLine() != null) {
			throw new Exception ("Esse tipo de tarefa não deve ter uma Data prazo");
		}
		return taskRepository.save(task);
	}

	public Task createTaskWtihDeadLine(Task task) throws Exception {
		if(task.getDeadLine() == null) {
			throw new Exception ("Esse tipo de tarefa deve ter um tempo prazo");
		}
		if(task.getDoDate() != null) {
			throw new Exception ("Esse tipo de tarefa não deve ter uma Data prazo");
		}
		return taskRepository.save(task);
	}
	
	public List<Task> createMultipleTaskWtihDeadLine(List<Task> listaTask){
		for (Task task : listaTask) {
			taskRepository.save(task);
		}
		return taskRepository.findAll();
	}
	public List<Task> createMultipleTaskWtihDoDate(List<Task> listaTask){
		for (Task task : listaTask) {
			taskRepository.save(task);
		}
		return taskRepository.findAll();
	}

	public Task createTask(Task novaTask) {
		return taskRepository.save(novaTask);
	}

	public Page<Task> findAllTask(Pageable pageable) {
		return (Page<Task>) taskRepository.findAll();
	}

	public Task findTask(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

	public Task updateTask(Task task) {

		Task task1 = taskRepository.findById(task.getId()).orElse(null);

		if (task1 != null) {
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
