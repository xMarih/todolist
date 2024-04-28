package com.br.todolist.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.todolist.entity.Task;
import com.br.todolist.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	public List<Task> findAllFreeTasks() {
		return taskRepository.findAllFreeTasks();
	}

	public List<Task> findTasksWtihDeadLine() {
		
		List<Task> listaTask = taskRepository.findTasksWtihDeadLine();
		LocalDate dataAtual = LocalDate.now();
		for (Task task : listaTask) {
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
		return listaTask;
		
		
	}

	public List<Task> findTasksWithDoDate() {
		List<Task> listaTask = taskRepository.findTasksWithDoDate();
		LocalDate dataAtual = LocalDate.now();

		for (Task task : listaTask) {
			LocalDate dataInicioTarefa = task.getStartDate().toLocalDate();

			if (dataAtual.isAfter(dataInicioTarefa))
				task.setStatus(
						"Sua tarefa está " + 
				ChronoUnit.DAYS.between(dataInicioTarefa, dataAtual) + "dias em atraso");
		}
		return listaTask;
	}

	public Task createTaskWithDoDate(Task task) {
		return taskRepository.save(task);
	}

	public Task createTaskWtihDeadLine(Task task) {
		return taskRepository.save(task);
	}

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
