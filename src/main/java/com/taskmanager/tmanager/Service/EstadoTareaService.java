package com.taskmanager.tmanager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.tmanager.Model.EstadoTarea;
import com.taskmanager.tmanager.Model.Tarea;
import com.taskmanager.tmanager.Repository.EstadoTareaRepository;


@Service
public class EstadoTareaService {
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    public EstadoTarea crearEstadoParaTarea(Tarea tarea, EstadoTarea estadoTarea) {
        estadoTarea.setEstadoActivo(true);
        estadoTarea.setTarea(tarea);
        return estadoTareaRepository.save(estadoTarea);
    }

    public void eliminaEstado(Long idEstado) {
        estadoTareaRepository.deleteById(idEstado);
    }
    
}
