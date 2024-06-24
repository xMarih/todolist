import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPenToSquare } from "@fortawesome/free-solid-svg-icons";
import { TableCell, TableRow, IconButton, Select, MenuItem, FormControl, InputLabel } from "@mui/material";
import { EditTodoForm } from "./EditTodoForm";
import { DeleteTodoForm } from "./DeleteTodoForm";
import todoService from '../services/todoService';

export const TodoList = ({ task, deleteTodo, editTodo, saveTodo }) => {
  const [isEditOpen, setIsEditOpen] = useState(false);
  const [isDeleteOpen, setIsDeleteOpen] = useState(false);
  const [currentTask, setCurrentTask] = useState(null);
  const [status, setStatus] = useState(task.status || "A_FAZER");

  const getType = () => {
    if (task.dueDate && task.dueDays && task.dueDays !== 0) return "Prazo";
    if (task.dueDate) return "Data";
    return "Livre";
  };

  const calculateDueDays = (dueDate) => {
    const currentDate = new Date();
    const dueDateObj = new Date(dueDate);
    const diffTime = dueDateObj - currentDate;
    return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  };

  const handleStatusChange = async (event) => {
    const newStatus = event.target.value;
    setStatus(newStatus);

    const updatedTask = {
      description: task.description,
      type: task.type,
      priority: task.priority,
      dueDate: task.dueDate,
      dueDays: task.dueDays,
      status: newStatus,
    };

    try {
      await todoService.updateTodo(task.id, updatedTask);
    } catch (error) {
      console.error('Erro ao atualizar o status da tarefa:', error);
    }
  };

  const handleEditOpen = (task) => {
    setCurrentTask(task);
    setIsEditOpen(true);
  };

  const handleEditClose = () => {
    setCurrentTask(null);
    setIsEditOpen(false);
  };

  const handleDeleteOpen = () => setIsDeleteOpen(true);
  const handleDeleteClose = () => setIsDeleteOpen(false);

  const handleDeleteConfirm = async () => {
    await deleteTodo(task.id);
    handleDeleteClose();
  };

  return (
    <>
      <TableRow>
        <TableCell>{task.description}</TableCell>
        <TableCell>{getType()}</TableCell>
        <TableCell>{task.type}</TableCell>
        <TableCell>{task.priority}</TableCell>
        <TableCell>{task.dueDate}</TableCell>
        <TableCell>{task.dueDays}</TableCell>
        <TableCell>
          <FormControl fullWidth>
            <InputLabel>Status</InputLabel>
            <Select value={status} onChange={handleStatusChange}>
              <MenuItem value="A_FAZER">A fazer</MenuItem>
              <MenuItem value="EM_PROGRESSO">Em progresso</MenuItem>
              <MenuItem value="FEITO">Feito</MenuItem>
              <MenuItem value="PENDENTE">Pendente</MenuItem>
            </Select>
          </FormControl>
        </TableCell>
        <TableCell>
          <IconButton onClick={() => handleEditOpen(task)}>
            <FontAwesomeIcon icon={faPenToSquare} />
          </IconButton>
          <IconButton onClick={handleDeleteOpen}>
            <FontAwesomeIcon icon={faTrash} />
          </IconButton>
        </TableCell>
      </TableRow>
      {currentTask && (
        <EditTodoForm
          task={currentTask}
          saveTodo={saveTodo}
          open={isEditOpen}
          onClose={handleEditClose}
        />
      )}
      <DeleteTodoForm
        open={isDeleteOpen}
        onClose={handleDeleteClose}
        onConfirm={handleDeleteConfirm}
        taskTitle={task.description}
      />
    </>
  );
};