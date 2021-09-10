package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Linea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,updatable = false)
    private Long productoId;

    @Column(nullable = false,updatable = false)
    private Integer cantidad;

    @Column(nullable = false,updatable = false)
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Orden orden;

    public Long getId() {return id;}
    public Long getProducto() {return productoId;}
    public Integer getCantidad() {return cantidad;}
    public Double getPrecio() {return precio;}
    @Transient
    public Double getSubTotal() {return precio*cantidad;}

    public void setProducto_id(Long producto_id) {this.productoId = producto_id;}
    public void setCantidad(Integer cantidad) {this.cantidad = cantidad;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public void setOrden(Orden orden) {this.orden = orden;}

}
