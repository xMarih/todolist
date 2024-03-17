package com.br.todolist.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Table(name = "subtask", schema = "public")

public class SubTask {
	
	@Id
	@Column(value = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id_sub_task")
	private Long id;

	@Column(value = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_task")
	private Task task;
	
	@Column(value = "done")
	private Boolean done;
	
	@Column(value = "deleted")
	private Boolean deleted;
	
	
}
