package com.example.demo.repository;

import com.example.demo.entity.Detalle;
import com.example.demo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {
    Detalle getById(Long id);
    Boolean existsByProducto(Producto producto);
}

