package com.br.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priorities {

    ALTA(1, "Alta"),
    MEDIA(2, "Média"),
    BAIXA(3, "Baixa");

	
	private Integer code;
	private String description;
}
