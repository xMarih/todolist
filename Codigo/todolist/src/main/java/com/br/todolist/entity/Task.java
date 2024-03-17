package com.br.todolist.entity;

import java.util.List;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@Table(name = "task", schema = "public")

public class Task {
	
	@Id
	@Column(value = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id_task")
	private Long id;

	@OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SubTask> listaSubTask;

	@Column(value = "description")
	private String description;
	
	@Column(value = "done")
	private Boolean done;
	
	@Column(value = "deleted")
	private Boolean deleted;

	public Task(Long id, List<SubTask> listaSubTask, String description, Boolean done, Boolean deleted) {
		super();
		this.id = id;
		this.listaSubTask = listaSubTask;
		this.description = description;
		this.done = done;
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SubTask> getListaSubTask() {
		return listaSubTask;
	}

	public void setListaSubTask(List<SubTask> listaSubTask) {
		this.listaSubTask = listaSubTask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
