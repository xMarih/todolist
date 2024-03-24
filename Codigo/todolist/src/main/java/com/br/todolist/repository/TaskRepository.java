package com.br.todolist.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.todolist.entity.Task;

@EntityScan
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

