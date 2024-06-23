package com.br.todolist.testIntegration;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.br.todolist.TodolistApplicationTests;

import io.restassured.RestAssured;

@ExtendWith(MockitoExtension.class)
@ExtendWith({ SpringExtension.class, MockitoExtension.class })

@SpringBootTest(classes = {
		TodolistApplicationTests.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TaskControllerIntegration {

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.port = 8080;
	}

	@Test
	public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
		get("/api/tasks").then().statusCode(200);
	}

	@Test
	public void givenUrl_whenSuccessOnGetsResponseAndJsonHasOneTask_thenCorrect() {
		get("/api/tasks/1").then().statusCode(200).assertThat().body("description", equalTo("Primeira tarefa"));
	}

}