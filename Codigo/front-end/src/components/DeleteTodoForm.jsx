import React from 'react';
import { Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Button, Typography } from '@mui/material';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';

export const DeleteTodoForm = ({ open, onClose, onConfirm, taskTitle }) => {
  return (
    <Dialog
      open={open}
      onClose={onClose}
      aria-labelledby="alert-dialog-title"
      aria-describedby="alert-dialog-description"
    >
      <DialogTitle id="alert-dialog-title" style={{ display: 'flex', alignItems: 'center' }}>
        <FontAwesomeIcon icon={faTrashAlt} style={{ marginRight: 8 }} />
        <Typography variant="h6">Excluir tarefa</Typography>
      </DialogTitle>
      <DialogContent>
        <DialogContentText id="alert-dialog-description">
          Deseja excluir a tarefa "{taskTitle}"?
        </DialogContentText>
      </DialogContent>
      <DialogActions style={{ justifyContent: 'center' }}>
        <Button onClick={onClose} variant="outlined" color="primary">
          Cancel
        </Button>
        <Button onClick={onConfirm} variant="contained" color="error" autoFocus>
          Delete
        </Button>
      </DialogActions>
    </Dialog>
  );
};
