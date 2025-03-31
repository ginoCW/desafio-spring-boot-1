package com.taskmanager.tmanager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taskmanager.tmanager.Model.Usuario;
import com.taskmanager.tmanager.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) {
        
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public void actualizarUsuario(Long idUsuario, String nombre){
        Optional<Usuario> usuarioActual = usuarioRepository.findById(idUsuario);
        if(usuarioActual != null){
            Usuario usuarioActualizado = usuarioActual.get();
            usuarioActualizado.setNombreUsuario(nombre);
            usuarioRepository.save(usuarioActualizado);
        }
    }

    public Usuario findUsuarioById(Long idUsuario){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(idUsuario);
        if(!usuarioEncontrado.isPresent()){
            return null;
        }
        return usuarioEncontrado.get();
    }

}
