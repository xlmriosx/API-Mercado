package com.example.demo.entity;
import com.example.demo.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = true)
    @NotBlank(message = "Debe ingresar un nombre.")
    @Size(min = 2, message = "El nombre debe tener minimo 2 caracteres")
    private String nombre;

    @Column(nullable = false, updatable = true)
    @NotBlank(message = "Debe ingresar un apellido.")
    @Size(min = 2, message = "El apellido debe tener minimo 2 caracteres")
    private String apellido;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion = UsuarioService.creacion();

    @Column(nullable = false,unique = true)
    @Email()
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private String pais;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("usuario")
    private List<Carrito> carritos = new ArrayList<>();


    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public Date getFechaCreacion() {return fechaCreacion;}
    public String getEmail() { return email;}
    public String pwd() { return password;}
    public String getCiudad() { return ciudad;}
    public String getProvincia() { return provincia;}
    public String getPais() { return pais;}
    public List<Carrito> getCarritos() {return carritos;}


    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setEmail(String email) { this.email = email;}
    public void setPassword(String password) { this.password = password;}
    public void setCiudad(String ciudad) { this.ciudad = ciudad;}
    public void setProvincia(String provincia) { this.provincia = provincia;}
    public void setPais(String pais) { this.pais = pais;}

}

