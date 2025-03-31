package com.taskmanager.tmanager.Controller;

import com.taskmanager.tmanager.Security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "none")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Logeo", 
               description = "Permite logearse a los usuarios registrados mediante JWT",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Token entregado ó Usuario Incorrecto",
                       content = @Content(mediaType = "text/plain")),
                   @ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(mediaType = "*/*"))
               })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) {
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }
}
