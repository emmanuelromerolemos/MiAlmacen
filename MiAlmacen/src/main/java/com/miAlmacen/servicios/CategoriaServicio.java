package com.miAlmacen.servicios;

import com.miAlmacen.entidades.Categoria;
import com.miAlmacen.repositorios.CategoriaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServicio {

    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    public String habilitarCategoria(String idCategoria) {

        Optional<Categoria> respuesta = categoriaRepositorio.findById(idCategoria);
        if (respuesta.isPresent()) {
            Categoria categoria = respuesta.get();
            categoria.setDisponibilidad(true);

            categoriaRepositorio.save(categoria);
            return "Se ha habilitado la categoría";
        }
        return "No se ha encontrado la categoría solicitada.";
    }

    public String deshabilitarCategoria(String idCategoria) {

        Optional<Categoria> respuesta = categoriaRepositorio.findById(idCategoria);
        if (respuesta.isPresent()) {
            Categoria categoria = respuesta.get();
            categoria.setDisponibilidad(false);

            categoriaRepositorio.save(categoria);
            return "Se ha deshabilitado la categoría.";

        }
        return "No se ha encontrado la categoría solicitada.";
    }
}