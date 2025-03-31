package com.taskmanager.tmanager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.tmanager.Model.EstadoTarea;
import com.taskmanager.tmanager.Model.Tarea;
import com.taskmanager.tmanager.Service.TareaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tarea")
@Tag(name = "Tareas", description = "Endpoints para la gestión de tareas")
@SecurityRequirement(name = "BearerAuth")
public class TareaController {
    
    @Autowired
    private TareaService tareaService;

    @Operation(summary = "Obtener todas las tareas", 
               description = "Retorna una lista de tareas",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida correctamente",
                       content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = Tarea.class))),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @GetMapping("/getAllTareas")
    public List<Tarea> getAllTasks(){
        return tareaService.getAllTareas();
    }

    @Operation(summary = "Crea una tarea", 
               description = "Crea la tarea ingresada al usuario indicado",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Tarea creada correctamente",
                       content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = Tarea.class))),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @PostMapping("/crearTarea")
    public ResponseEntity<Tarea> crearTarea(@RequestParam Long idUsuario, @RequestBody Tarea tarea){
        Tarea tareaGuardada = tareaService.crearTarea(idUsuario,tarea);
        return ResponseEntity.ok(tareaGuardada);
    }
    
    @Operation(summary = "Elimina una Tarea", 
               description = "Elimina una tarea segun su id",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Tarea eliminada correctamente",
                       content = @Content(mediaType = "application/json")),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @DeleteMapping("/eliminarTarea")
    public ResponseEntity<String> eliminarUsuario(@RequestParam Long idTarea){
        tareaService.eliminarTarea(idTarea);
        return ResponseEntity.ok("{\"result\": \"Eliminado correctamente\"}");
    }

    @Operation(summary = "Actualiza una tarea", 
               description = "Permite actualizar el titulo y/o descripción de una tarea",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Tarea actualizada correctamente",
                       content = @Content(mediaType = "application/json")),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @PatchMapping("/actualizarTarea")
    public ResponseEntity<String> actualizarTarea(@RequestParam Long idTarea, String titulo, String descripcion){
        tareaService.actualizarTarea(idTarea, titulo, descripcion);
        return ResponseEntity.ok("{\"result\": \"Actualizado correctamente\"}");
    }
    
    @Operation(summary = "Agrega un estado a la tarea indicada", 
               description = "Agrega un estado a la tarea indicada y lo marca como estado activo y marca todos los demas estados como inactivos",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Estado creado correctamente",
                       content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = Tarea.class))),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @PostMapping("/agregarEstadoTarea")
    public ResponseEntity<Tarea> crearEstadoTarea(@RequestParam Long idTarea, @RequestBody EstadoTarea estadoTarea){
        Tarea tareaActualizada = tareaService.agregarEstadoTarea(idTarea,estadoTarea);
        return ResponseEntity.ok(tareaActualizada);
    }
    
    @Operation(summary = "Elimina un Estado de una Tarea", 
               description = "Elimina un Estado de una Tarea segun su id",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Estado eliminado correctamente",
                       content = @Content(mediaType = "application/json")),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @DeleteMapping("/eliminarEstado")
    public ResponseEntity<String> eliminarEstado(@RequestParam Long idEstado){
        tareaService.eliminarEstadoTarea(idEstado);
        return ResponseEntity.ok("{\"result\": \"Eliminado correctamente\"}");
    }
}
