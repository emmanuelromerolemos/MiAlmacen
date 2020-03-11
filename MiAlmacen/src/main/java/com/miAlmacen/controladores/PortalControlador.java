package com.miAlmacen.controladores;

import com.miAlmacen.entidades.Categoria;
import com.miAlmacen.entidades.Producto;
import com.miAlmacen.repositorios.CategoriaRepositorio;
import com.miAlmacen.repositorios.ProductoRepositorio;
import com.miAlmacen.servicios.ProductoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")

public class PortalControlador {
	
        
    @Autowired
    ProductoServicio productoServicio;
    
    @Autowired
    CategoriaRepositorio categoriaRepositorio;
    
    @Autowired
    ProductoRepositorio productoRepositorio;
    
    @GetMapping("/")
	public String index() {
		return "index.html";
	}
        
    @GetMapping("/adminProducto")
    public String adminProducto(ModelMap modelo){
        List <Producto> listaPro=productoRepositorio.findAll();
        List <Categoria> listaCategoria=categoriaRepositorio.findAll();
        modelo.put("listaPro", listaPro);
        modelo.put("listaCategoria", listaCategoria);
       return "adminProducto.html";
    }    
    
    @GetMapping("/registrar")   
    public String registrar(){
        return "guardarPro.html";
    }
    
    @GetMapping("/resgitroProducto")
    public String resgitroProducto(ModelMap modelo){
        List <Categoria> listaCategoria=categoriaRepositorio.findAll();
        modelo.put("listaCategoria", listaCategoria);
        return "registrarPro.html";
    }
    
    @PostMapping("/guardar")
    public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam Double precio, @RequestParam String idCategoria){
        String mensajeError=productoServicio.carga(nombre, precio, idCategoria);
        if (mensajeError==null) {
            List <Producto> listaPro=productoRepositorio.findAll();
            List <Categoria> listaCategoria=categoriaRepositorio.findAll();
            modelo.put("listaCategoria", listaCategoria);
            modelo.put("listaPro", listaPro);
            return "adminProducto.html";
        }else{
            modelo.put("mensajeError", mensajeError);
            modelo.put("nombre", nombre);
            modelo.put("precio", precio);
            List <Categoria> listaCategoria=categoriaRepositorio.findAll();
            modelo.put("listaCategoria", listaCategoria);
            return "registrarPro.html";
        }
    }
    
    
    
    @GetMapping("/modificar")
    public String modificar(ModelMap modelo, @RequestParam String id){
        Producto producto=productoRepositorio.findById(id).get();
        List<Categoria> listaCategoria=categoriaRepositorio.findAll();
        modelo.put("idProducto", id);
        modelo.put("nombre", producto.getNombre());
        modelo.put("precio", producto.getPrecio());
        modelo.put("listaCategoria", listaCategoria);
        return "modificarPro.html";
    }
    
    @PostMapping("/modificado")
    public String modificado(ModelMap modelo, @RequestParam String id, @RequestParam String nombre, @RequestParam Double precio, @RequestParam String idCategoria ){
        String mensajeError=productoServicio.modificar(id, nombre, precio, idCategoria);
        if (mensajeError==null) {
        List <Producto> listaPro=productoRepositorio.findAll();
        modelo.put("listaPro", listaPro);
        List <Categoria> listaCategoria=categoriaRepositorio.findAll();
        modelo.put("listaCategoria", listaCategoria);
        return "adminProducto.html";
        }else{
            modelo.put("mensajeError", mensajeError);
            List<Categoria> listaCategoria=categoriaRepositorio.findAll();
            modelo.put("idProducto", id);
            modelo.put("nombre", nombre);
            modelo.put("precio", precio);
            modelo.put("listaCategoria", listaCategoria);
            return "modificarPro.html";
        }
    }
    
    @GetMapping("/mostrarByCategoria")
    public String mostrarByCategoria(ModelMap modelo, @RequestParam String idCategoria){
        List <Producto> listaPro=productoRepositorio.findProByCate(idCategoria);
        modelo.put("listaPro", listaPro);
        List <Categoria> listaCategoria=categoriaRepositorio.findAll();
        modelo.put("listaCategoria", listaCategoria);
        return "adminProducto.html";      
    }
    
    
    @GetMapping("/eliminar")
    public String eliminar(ModelMap modelo, @RequestParam String id){
        productoRepositorio.deleteById(id);
        List <Producto> listaPro=productoRepositorio.findAll();
        modelo.put("listaPro", listaPro);
        List <Categoria> listaCategoria=categoriaRepositorio.findAll();
        modelo.put("listaCategoria", listaCategoria);
        return "adminProducto.html";      
    }
    
    @GetMapping("/disponible")
    public String disponible(ModelMap modelo, @RequestParam String id){
        productoServicio.habiDeshabi(id);
        List <Producto> listaPro=productoRepositorio.findAll();
        modelo.put("listaPro", listaPro);
        List <Categoria> listaCategoria=categoriaRepositorio.findAll();
        modelo.put("listaCategoria", listaCategoria);
        return "adminProducto.html";
    }
    
}
    



