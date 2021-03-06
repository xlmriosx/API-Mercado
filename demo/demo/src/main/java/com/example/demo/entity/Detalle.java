package com.example.demo.entity;
import javax.persistence.*;

@Entity
public class Detalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "producto", referencedColumnName = "id")
    private Producto producto;

    @Column(nullable = false,updatable = true)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Carrito carrito;

    //Getters
    public Long getId() {return id;}
    public Producto getProducto() {return producto;}
    public Integer getCantidad() {return cantidad;}
    @Transient
    public Double getSubTotal() {return producto.getPrecio_unitario()*cantidad;}

    //Setters
    public void setProducto(Producto producto) {this.producto = producto;}
    public void setCarrito(Carrito carrito) {this.carrito = carrito;}
    public void incCantidad() {this.cantidad += 1;}
    public void decCantidad() {this.cantidad -= 1;}

    public Detalle() {
        this.cantidad = 1;
    }
}