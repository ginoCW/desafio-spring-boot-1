package com.taskmanager.tmanager.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la tarea", example = "1", hidden = true)
    private Long idTarea;

    @Schema(description = "Titulo de la tarea", example = "Subir proyecto a git")
    private String titulo;

    @Schema(description = "Descripción de la tarea", example = "Se debe subir el desafio de codigo al git enviado por HF Solutions")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    @JsonBackReference
    @Schema(hidden = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @ArraySchema(schema = @Schema(implementation = EstadoTarea.class))
    private List<EstadoTarea> estadoTarea;

    public Long getIdTarea() {
        return idTarea;
    }
    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<EstadoTarea> getEstadoTarea() {
        return estadoTarea;
    }
    public void setEstadoTarea(List<EstadoTarea> estadoTarea) {
        this.estadoTarea = estadoTarea;
    }
}
