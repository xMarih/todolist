import React, { useState, useEffect } from "react";
import { TodoForm } from "./TodoForm";
import { TodoList } from "./TodoList";
import todoService from '../services/todoService';
import { Button, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Box } from "@mui/material";

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
    <div className="TodoWrapper" style={{ marginTop: '20px', border: '1px solid #ccc', borderRadius: '8px', padding: '20px' }} >
      <Box display="flex" justifyContent="center" alignItems="center" mb={2}>
        <Typography variant="h3" component="h2">
          Minhas Lista de Tarefas
        </Typography>
      </Box>
      <Box display="flex" justifyContent="flex-end" sx={{ marginBottom: 2 }}>
        <Button
          variant="outlined"
          onClick={() => setIsTodoFormOpen(true)}
          sx={{
            color: 'black',
            borderColor: 'black',
            backgroundColor: 'white',
            '&:hover': {
              backgroundColor: 'black',
              color: 'white',
              borderColor: 'black'
            }
          }}
        >
          Adicionar Tarefa
        </Button>
      </Box>
      <TableContainer component={Paper} style={{ border: '1px solid #ccc', borderRadius: '8px', }}>
        <Table >
          <TableHead>
            <TableRow>
              <TableCell>Nome da Tarefa</TableCell>
              <TableCell>Tipo</TableCell>
              <TableCell>Tipo de Task</TableCell>
              <TableCell>Prioridade</TableCell>
              <TableCell>Data de Vencimento</TableCell>
              <TableCell>Prazo</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Editar</TableCell>
              <TableCell>Excluir</TableCell>
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
