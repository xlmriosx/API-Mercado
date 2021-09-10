package com.example.demo.entity;

import com.example.demo.service.CarritoService;
import com.example.demo.service.OrdenService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToMany(mappedBy = "orden",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Linea> linea = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Long carritoId;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_creacion = CarritoService.creacion();

    @Column(unique = true,nullable = false)
    private Long numero;

    @Column(nullable = false, length=200)
    private String observacion;

    //Setters
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public void addLinea(Linea linea) {this.linea.add(linea);}
    public void setCarritoId(Long carritoId) {this.carritoId = carritoId;}
    public void setNumero(Long numero) {this.numero = numero;}
    public void setObservacion(String observacion) {this.observacion = observacion;}

    public String getObservacion() { return observacion;}
    public Long getId() {return id;}
    public Long getUsuarioId(){return usuario.getId();}
    @JsonIgnore
    public Usuario getUsuario() {return usuario;}
    public List<Linea> getLinea() {return linea;}
    public Long getCarritoId() {return carritoId;}
    public Date getFecha_creacion() {return fecha_creacion;}
    public Long getNumero() {return numero;}

    @Transient
    public Double getCostoTotal(){
        Double total = 0.0;
        for ( Linea l : this.getLinea()){
            total = l.getSubTotal() + total;
        }
        return total;
    }

}
