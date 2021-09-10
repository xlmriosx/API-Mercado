package com.example.demo.service;

import com.example.demo.entity.Carrito;
import com.example.demo.entity.Detalle;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.DetalleRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class CarritoService {
    @Autowired
    private static CarritoRepository carritoRepository;

    @Autowired
    private static UsuarioRepository usuarioRepository;

    @Autowired
    private static ProductoRepository productoRepository;

    @Autowired
    private static DetalleRepository detalleRepository;



    public static Date creacion(){
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static Boolean nuevo_carrito(Usuario usuario){
        List<Carrito> carritos_del_user = usuario.getCarritos();
        if (carritos_del_user.size() >=1) {
            Carrito ultimo = carritos_del_user.get(carritos_del_user.size() - 1);
            ultimo.setEstado(false);
        }
        return true;
    }

    public static Object evaluarCerrarCarrito(Carrito carrito){
        if (carrito.getDetalle().size()>=1) {
            carrito.setEstado(false);
            carritoRepository.save(carrito);
            return new ResponseEntity<>(carrito.getDetalle(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Su carrito no tiene productos, no se puede cerrar",HttpStatus.CONFLICT);
        }
    }

    public static void hacerCarritoComprado(Carrito carrito){
        carrito.setEstado(false);
        carritoRepository.save(carrito);
    }

}
