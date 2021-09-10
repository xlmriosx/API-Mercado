package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario getById(Long id);
    List<Usuario> getByCiudad(String ciudad);
    List<Usuario> getByFechaCreacionAfter(Date fetch);
    Boolean existsByEmail(String email);
}