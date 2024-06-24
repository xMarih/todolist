import React, { useState, useEffect } from "react";
import { TodoForm } from "./TodoForm";
import { TodoList } from "./TodoList";
import todoService from '../services/todoService';
import { Button, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

export const TodoWrapper = () => {
  const [todos, setTodos] = useState([]);
  const [isTodoFormOpen, setIsTodoFormOpen] = useState(false);

  const fetchTodos = async () => {
    try {
      const response = await todoService.getTodo();
      setTodos(response.data);
    } catch (error) {
      console.error('Erro ao buscar todos:', error);
    }
  };

  useEffect(() => {
    fetchTodos();
  }, []);

  const addTodo = async (todo) => {
    try {
      await todoService.criarTodo(todo);
      await fetchTodos();
    } catch (error) {
      console.error('Erro ao adicionar a tarefa:', error);
    }
  };

  const deleteTodo = async (id) => {
    try {
      await todoService.deleteTodo(id);
      await fetchTodos();
    } catch (error) {
      console.error('Erro ao excluir a tarefa:', error);
    }
  };

  const editTodo = (id) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, isEditing: !todo.isEditing } : todo
      )
    );
  };

  const saveTodo = async (id, updatedTask) => {
    try {
      await todoService.updateTodo(id, updatedTask);
      await fetchTodos();
    } catch (error) {
      console.error('Erro ao atualizar a tarefa:', error);
    }
  };

  return (
    <div className="TodoWrapper">
      <Typography variant="h3" component="h2">
        Lista de Tarefas
      </Typography>
      <Button variant="contained" color="primary" onClick={() => setIsTodoFormOpen(true)} sx={{ marginBottom: 2 }}>
        Adicionar Tarefa
      </Button>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Nome da Tarefa</TableCell>
              <TableCell>Tipo</TableCell>
              <TableCell>Tipo de Task</TableCell>
              <TableCell>Prioridade</TableCell>
              <TableCell>Data de Vencimento</TableCell>
              <TableCell>Prazo</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Ações</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {todos.map((todo) => (
              <TodoList
                key={todo.id}
                task={todo}
                deleteTodo={deleteTodo}
                editTodo={editTodo}
                saveTodo={saveTodo}
              />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TodoForm addTodo={addTodo} open={isTodoFormOpen} onClose={() => setIsTodoFormOpen(false)} />
    </div>
  );
};
