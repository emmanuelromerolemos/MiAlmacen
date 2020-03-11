

package com.miAlmacen.repositorios;

import com.miAlmacen.entidades.Categoria;
import com.miAlmacen.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, String>{
@Query("Select c from Categoria c")
    public List<Producto> buscatTodos();
    
}
