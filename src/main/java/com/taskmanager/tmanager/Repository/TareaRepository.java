package com.taskmanager.tmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskmanager.tmanager.Model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    
}
