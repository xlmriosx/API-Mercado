package com.example.demo.repository;

import com.example.demo.entity.Linea;
import com.example.demo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LineaRepository extends JpaRepository<Linea, Long> {
    Linea getById(Long id);
    Boolean existsByProductoId(Long producto_id);
}