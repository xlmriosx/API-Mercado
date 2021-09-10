package com.example.demo.entity;

import com.example.demo.service.UsuarioService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Debe ingresar un nombre.")
    @Size(min = 2, message = "El nombre debe tener minimamente 2 caracteres")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "Debe ingresar una descripcion.")
    @Size(min = 5, message = "La descripcion debe tener minimamente 5 caracteres")
    private String descripcion;

    @Column(nullable = false,precision = 2, scale = 0)
    private Double precio_unitario;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_creacion = UsuarioService.creacion();

    @Column(nullable = false)
    private Boolean publicado=true;

    //Get
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public String getDescripcion() {return descripcion;}
    public Double getPrecio_unitario() {return precio_unitario;}
    public String getContenido() {return contenido;}
    public Date getFecha_creacion() {return fecha_creacion;}
    public Boolean getPublicado() {return publicado;}



    //Set
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
    public void setContenido(String contenido){ this.contenido = contenido;}
    public void setPublicado(Boolean publicado) { this.publicado = publicado;}


}







