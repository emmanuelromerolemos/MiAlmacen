package com.miAlmacen.servicios;

import com.miAlmacen.entidades.Usuario;
import com.miAlmacen.repositorios.UsuarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public String crearUsuario(String nombre) {
        String mensajeError = datosOk(nombre);
        if (mensajeError == null) {
            Usuario usuario = new Usuario(nombre);
            usuarioRepositorio.save(usuario);
        }
        return mensajeError;
    }

    public String modificarUsuario(String id, String nombre) {
        String mensajeError = datosOk(nombre);
        if (mensajeError == null) {
            Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
            if (respuesta.isPresent()) {
                
                Usuario usuario = respuesta.get();
                usuario.setNombreUsuario(nombre);

                usuarioRepositorio.save(usuario);
            }
        }
        return mensajeError;
    }
    
    public String eliminarUsuario (String id){
        Optional <Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Usuario usuario = respuesta.get();
            
            usuarioRepositorio.delete(usuario);
            return "El usuario se ha eliminado correctamente.";
        }
        return "No se ha encontrado el usuario solicitado.";
    }

    public String datosOk(String nombre) {

        if (nombre == null || nombre.isEmpty()) {
            return "El campo no puede estar vacío.";
        }
        return null;
    }
}
