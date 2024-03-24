package com.br.todolist.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private Boolean done;

	private Boolean deleted;


}
