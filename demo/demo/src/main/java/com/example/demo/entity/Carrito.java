package com.example.demo.entity;
import com.example.demo.service.CarritoService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String generado_por;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_creacion = CarritoService.creacion();

    @Column(nullable = true)
    private Number total;
    //HACER METODO MEDIANTE DETALLE

    @Column(nullable = false)
    private Boolean estado = true;

    @OneToMany(mappedBy = "carrito",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detalle> detalle = new ArrayList<>();

    @Transient
    public Double getCostoTotal(){
        Double total = 0.0;
        for ( Detalle d : this.getDetalle()){
            total = d.getSubTotal() + total;
        }
        return total;
    }

    public void addDetalle(Detalle detalle){this.getDetalle().add(detalle);}
    public void removeDetalle(Detalle detalle){
        this.getDetalle().remove(detalle);
    }



    public Long getId() {
        return id;
    }

    public String getGenerado_por() {
        return generado_por;
    }

    public void setGenerado_por(String generado_por) {
        if(generado_por==""){
            this.generado_por="Pagina Web";
        }else {
            this.generado_por=generado_por;
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}