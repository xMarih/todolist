package com.br.todolist.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.todolist.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query(value = "select now() ", nativeQuery = true)
	Date dataBanco();
}
