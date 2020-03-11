
package com.miAlmacen.repositorios;

import com.miAlmacen.entidades.Producto;
import com.miAlmacen.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
@Query("Select c from Usuario c")
    public List<Producto> buscatTodos();
    
}
