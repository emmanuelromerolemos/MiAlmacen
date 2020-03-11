
package com.miAlmacen.repositorios;

import com.miAlmacen.entidades.Producto;
import com.miAlmacen.entidades.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, String> {
@Query("Select c from Venta c")
    public List<Producto> buscatTodos();
    
}
