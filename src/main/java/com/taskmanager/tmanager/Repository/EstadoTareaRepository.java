package com.taskmanager.tmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskmanager.tmanager.Model.EstadoTarea;

public interface EstadoTareaRepository  extends JpaRepository<EstadoTarea, Long>{
    
}
