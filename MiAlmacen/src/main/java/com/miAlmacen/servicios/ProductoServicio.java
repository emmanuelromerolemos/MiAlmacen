package com.miAlmacen.servicios;

import com.miAlmacen.entidades.Categoria;
import com.miAlmacen.entidades.Producto;
import com.miAlmacen.repositorios.CategoriaRepositorio;
import com.miAlmacen.repositorios.ProductoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maxi
 */
@Service
public class ProductoServicio {
    @Autowired
    ProductoRepositorio productoRepositorio;
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    
    public String carga(String nombre, Double precio, String idCategoria){
  
        String mensajeError=datosOk(nombre, precio);
        if (mensajeError==null) {
            Optional <Categoria> respuesta=categoriaRepositorio.findById(idCategoria);
            if (respuesta.isPresent()) {
                Categoria categoria=respuesta.get();
                Producto producto=new Producto(nombre, precio, categoria);
                productoRepositorio.save(producto);
            }else{
                return "No se encontró la Categoria seleccionada";
            }
                       
        }
        return mensajeError;
    }
    
    
    public String modificar(String idProducto, String nombre, Double precio, String idCategoria){
        String mensajeError=datosOk(nombre, precio);
        if (mensajeError==null) {
            Optional <Producto> respuesta=productoRepositorio.findById(idProducto);
            Optional <Categoria> respuesta2=categoriaRepositorio.findById(idCategoria);
            if (respuesta.isPresent()) {
                if (respuesta2.isPresent()) {
                    Producto producto=respuesta.get();
                    Categoria categoria=respuesta2.get();
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setCategoria(categoria);
                    productoRepositorio.save(producto);
                }else{
                    return "No se encontró la categoria seleccionada";
                }
                
            }else{
                return "No se encontró el producto especificado";
            }
        }
        return mensajeError;
    }
    
    public String eliminar(String id){
        Optional <Producto> respuesta=productoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Producto producto=respuesta.get();
            productoRepositorio.delete(producto);
            return "El producto se ha eliminado correctamente";
        }
        return "No se ha encontrado el producto solicitado";
    }
    
    public String habilitar(String id){
        Optional <Producto> respuesta=productoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Producto producto=respuesta.get();
            producto.setDisponibilidad(true);
            productoRepositorio.save(producto);
            return "Se ha habilitado el producto";
        }
        return "No se ha encontrado el producto solicitado";
    }
    
    public void habiDeshabi(String id){
        Producto producto=productoRepositorio.findById(id).get();
        if (producto.isDisponibilidad()) {
            producto.setDisponibilidad(false);           
        }else{
            producto.setDisponibilidad(true);
        }
        productoRepositorio.save(producto);
    }
    
    public String deshabilitar(String id){
         Optional <Producto> respuesta=productoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Producto producto=respuesta.get();
            producto.setDisponibilidad(false);
            productoRepositorio.save(producto);
            return "Se ha deshabilitado el producto";
        }
        return "No se ha encontrado el producto solicitado";
    }
    
    
    
    public String datosOk(String nombre, Double precio){
        String mensaje=datosNull(nombre, precio);
        if (mensaje==null) {
            if (precio<=0) {
                return "El precio deber ser mayor a cero";
            }

        }
        return mensaje;
    }
    
    public String datosNull(String nombre, Double precio){
        if (nombre==null || nombre.isEmpty()) {
            return "El campo nombre no puede estar vacio";
        }
        if (precio==null) {
            return "El campo precio no puede estar vacio";
        }             
        return null;
    }
}