package com.br.todolist.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.todolist.entity.Task;

@EntityScan
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//	@Query("FROM Task WHERE done = true OR (doDate IS NULL AND deadline IS NULL)")
//	List<Task> findAllFreeTasks();
//
//	@Query("From Task where deadLine is not null order by deadLine")
//	List<Task> findTasksWtihDeadLine();
//
//	@Query("FROM Task WHERE doDate IS NOT NULL ORDER BY doDate")
//	List<Task> findTasksWithDoDate();
}

