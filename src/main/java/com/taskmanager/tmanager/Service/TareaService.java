package com.taskmanager.tmanager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.tmanager.Model.EstadoTarea;
import com.taskmanager.tmanager.Model.Tarea;
import com.taskmanager.tmanager.Model.Usuario;
import com.taskmanager.tmanager.Repository.TareaRepository;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;
    
    private final UsuarioService usuarioService;
    private final EstadoTareaService estadoTareaService;

    public TareaService(UsuarioService usuarioService, EstadoTareaService estadoTareaService) {
        this.usuarioService = usuarioService;
        this.estadoTareaService = estadoTareaService;
    }

    public List<Tarea> getAllTareas(){
        return tareaRepository.findAll();
    }

    public Tarea crearTarea(Long idUsuario,Tarea tarea){ 
        Usuario usuarioReferenciado = usuarioService.findUsuarioById(idUsuario);
        if(usuarioReferenciado != null){
            tarea.setUsuario(usuarioReferenciado);
            return tareaRepository.save(tarea);
        }
        
        return null;
    }

    public void eliminarTarea(Long idTarea){
        tareaRepository.deleteById(idTarea);
    }

    public void actualizarTarea(Long idTarea, String titulo, String descripcion){
        Optional<Tarea> tareaActual = tareaRepository.findById(idTarea);
        if(tareaActual != null){
            Tarea tareaActualizada = tareaActual.get();
            if(titulo != null && !titulo.isEmpty()){
                tareaActualizada.setTitulo(titulo);
            }
            if(descripcion != null && !descripcion.isEmpty()){
                tareaActualizada.setDescripcion(descripcion);
            }
            tareaRepository.save(tareaActualizada);
        }
    }

    public Tarea agregarEstadoTarea(Long tareaId, EstadoTarea nuevoEstado) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        tarea.getEstadoTarea().forEach(estado -> estado.setEstadoActivo(false));
        EstadoTarea estadoNuevo = estadoTareaService.crearEstadoParaTarea(tarea, nuevoEstado);
        tarea.getEstadoTarea().add(estadoNuevo);
        return tareaRepository.save(tarea);
    }

    public void eliminarEstadoTarea(Long estadoId) {
        estadoTareaService.eliminaEstado(estadoId);
    }
}
