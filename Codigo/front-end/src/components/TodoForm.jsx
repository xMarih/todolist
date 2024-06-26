import React, { useState } from "react";
import { Modal, Box, TextField, Button, MenuItem, Select, InputLabel, FormControl, Typography, DialogTitle } from "@mui/material";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';

export const TodoForm = ({ addTodo, open, onClose }) => {
  const [description, setDescription] = useState("");
  const [priority, setPriority] = useState("");
  const [taskType, setTaskType] = useState("");
  const [type, setType] = useState("LIVRE");
  const [dueDate, setDueDate] = useState("");
  const [dueDays, setDueDays] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newTask = {
      description,
      type: taskType,
      priority,
      dueDate: type === "DATA" ? dueDate : type === "PRAZO" ? new Date(Date.now() + dueDays * 24 * 60 * 60 * 1000).toISOString().split('T')[0] : null,
      dueDays: type === "PRAZO" ? dueDays : null,
    };

    await addTodo(newTask);
    onClose();
  };

  return (
    <Modal open={open} onClose={onClose}>
      <Box sx={{ ...modalStyle, width: 400 }}>

      <DialogTitle id="alert-dialog-title" style={{ display: 'flex', alignItems: 'center' }}>
          <FontAwesomeIcon icon={faCirclePlus} style={{ marginRight: 8 }} />
          <Typography variant="h6" component="h2">Criar Tarefa</Typography>
        </DialogTitle>

        <form onSubmit={handleSubmit}>
          <TextField
            label="Descrição da Tarefa"
            fullWidth
            margin="normal"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
          <FormControl fullWidth margin="normal">
            <InputLabel>Prioridade</InputLabel>
            <Select value={priority} onChange={(e) => setPriority(e.target.value)}>
              <MenuItem value="BAIXA">BAIXA</MenuItem>
              <MenuItem value="MEDIA">MEDIA</MenuItem>
              <MenuItem value="ALTA">ALTA</MenuItem>
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel>Tipo de Task</InputLabel>
            <Select value={taskType} onChange={(e) => setTaskType(e.target.value)}>
              <MenuItem value="NOVIDADE">1</MenuItem>
              <MenuItem value="BUG">2</MenuItem>
              <MenuItem value="APRIMORAMENTO">3</MenuItem>
            </Select>
          </FormControl>
          <FormControl fullWidth margin="normal">
            <InputLabel>Tipo</InputLabel>
            <Select value={type} onChange={(e) => setType(e.target.value)}>
              <MenuItem value="LIVRE">LIVRE</MenuItem>
              <MenuItem value="DATA">DATA</MenuItem>
              <MenuItem value="PRAZO">PRAZO</MenuItem>
            </Select>
          </FormControl>
          {type === "DATA" && (
            <TextField
              label="Data de Vencimento"
              type="date"
              fullWidth
              margin="normal"
              InputLabelProps={{ shrink: true }}
              value={dueDate}
              onChange={(e) => setDueDate(e.target.value)}
              inputProps={{ min: new Date().toISOString().split("T")[0] }}
            />
          )}
          {type === "PRAZO" && (
            <TextField
              label="Dias para Concluir"
              type="number"
              fullWidth
              margin="normal"
              value={dueDays}
              onChange={(e) => setDueDays(e.target.value)}
              inputProps={{ min: 1 }}
            />
          )}
          <Button type="submit" variant="outlined" color="primary"
            sx={{
              color: 'black',
              borderColor: 'black',
              backgroundColor: 'white',
              '&:hover': {
                backgroundColor: 'black',
                color: 'white',
                borderColor: 'black'
              }
            }}>
            Adicionar
          </Button>
        </form>
      </Box>
    </Modal>
  );
};

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};
