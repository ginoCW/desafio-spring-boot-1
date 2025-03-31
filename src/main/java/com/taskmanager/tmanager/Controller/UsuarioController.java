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

import com.taskmanager.tmanager.Model.Usuario;
import com.taskmanager.tmanager.Service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios")
@SecurityRequirement(name = "BearerAuth")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios", 
               description = "Retorna una lista de usuarios",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente",
                       content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = Usuario.class))),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @GetMapping("/getAllUsuarios")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @Operation(summary = "Crea un usuario", 
               description = "Crea el usuario ingresado con o sin tareas",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Usuario creado correctamente",
                       content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = Usuario.class))),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @PostMapping("/crearUsuario")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        Usuario usuarioGuardado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(usuarioGuardado);
    }

    @Operation(summary = "Elimina un usuario", 
               description = "Elimina un usuario segun su id",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente",
                       content = @Content(mediaType = "application/json")),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @DeleteMapping("/eliminarUsuario")
    public ResponseEntity<String> eliminarUsuario(@RequestParam Long idUsuario){
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("{\"result\": \"Eliminado correctamente\"}");
    }

    @Operation(summary = "Actualiza un usuario", 
               description = "Permite actualizar el nombre de un usuario",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                       content = @Content(mediaType = "application/json")),
                   @ApiResponse(responseCode = "403", description = "Falta autenticarse",content = @Content(mediaType = "*/*")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @PatchMapping("/actualizarUsuario")
    public ResponseEntity<String> actualizarUsuario(@RequestParam Long idUsuario, String nombre){
        usuarioService.actualizarUsuario(idUsuario, nombre);
        return ResponseEntity.ok("{\"result\": \"Actualizado correctamente\"}");
    }
}
    
    