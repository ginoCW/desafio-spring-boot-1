package com.taskmanager.tmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskmanager.tmanager.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}