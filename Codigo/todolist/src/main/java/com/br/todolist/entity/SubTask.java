package com.br.todolist.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class SubTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private Task task;

	private Boolean done;

	private Boolean deleted;

}
