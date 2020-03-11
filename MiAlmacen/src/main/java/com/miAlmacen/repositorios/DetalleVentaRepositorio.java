
package com.miAlmacen.repositorios;

import com.miAlmacen.entidades.DetalleVenta;
import com.miAlmacen.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepositorio extends JpaRepository<DetalleVenta, String> {
@Query("Select c from DetalleVenta c")
    public List<Producto> buscatTodos();
    
}
