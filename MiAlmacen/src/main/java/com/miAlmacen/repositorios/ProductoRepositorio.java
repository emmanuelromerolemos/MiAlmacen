

package com.miAlmacen.repositorios;

import com.miAlmacen.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {
    @Query("Select c from Producto c")
    public List<Producto> buscatTodos();
    
    @Query("SELECT p FROM Producto p WHERE p.categoria.id=:idCategoria ORDER BY p.nombre")
    public List<Producto> findProByCate( @Param("idCategoria")String idCategoria);
    

}
