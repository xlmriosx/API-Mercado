package com.example.demo.controller;

import com.example.demo.entity.Carrito;
import com.example.demo.entity.Detalle;
import com.example.demo.entity.Orden;
import com.example.demo.repository.CarritoRepository;
import com.example.demo.repository.OrdenRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.example.demo.service.LineaService.crearLinea;

@Controller
public class OrdenController {
    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/orden/{id_carrito}")
    public Object crearOrden(@PathVariable("id_carrito") Long id_carrito,@RequestBody Orden orden){
        Carrito carrito = carritoRepository.findById(id_carrito).orElse(null);
        if (carrito == null){
            return new ResponseEntity<>("No existe carrito con el id indicado", HttpStatus.NOT_FOUND);
        }else {
            if (carrito.getEstado() && (carrito.getDetalle().size() >= 1)) {
                orden.setCarritoId(id_carrito);
                orden.setUsuario(carrito.getUsuario());
                orden.setObservacion(orden.getObservacion());
                //cargarLineas(carrito, orden);
                List<Detalle> detalles_del_carrito = carrito.getDetalle();
//                for  (Detalle d : detalles_del_carrito) {
//                    orden.addLinea(crearLinea(d,orden));
//                    ordenRepository.save(orden);
//                }
//                //hacerCarritoComprado(carrito);
                carrito.setEstado(false);
                carritoRepository.save(carrito);
                return new ResponseEntity<>(ordenRepository.save(orden),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Este carrito ya esta cerrado o esta vacio", HttpStatus.CONFLICT);
            }
        }
    }

    @GetMapping(value = "/orden")
    public List<Orden> verOrdenes(){
        return  ordenRepository.findAll();
    }

    @GetMapping(value = "/orden/{id_orden}")
    public Object verOrden(@PathVariable("id_orden") Long id_orden){
        Orden orden = ordenRepository.findById(id_orden).orElse(null);
        if (orden == null){
            return new ResponseEntity<>("No existe una orden con el id indicado", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(orden,HttpStatus.OK);
        }
    }
//
//    @PutMapping(value = "usuario/{id_usuario}/orden/{id_orden}/close")
//    public Object cancelarOrden(@PathVariable("id_orden") Long id_orden, @PathVariable("id_usuario") Long id_usuario){
//        Orden orden = ordenRepository.findById(id_orden).orElse(null);
//        if (orden == null){
//            return new ResponseEntity<>("No existe una orden con el id indicado", HttpStatus.NOT_FOUND);
//        }else{
//            //return new ResponseEntity<>(tratarCancelarOrden(orden, id_usuario),HttpStatus.OK);
//            Usuario usuario = usuarioRepository.findById(id_usuario).orElse(null);
//            if (usuario != null) {
//                if ((usuario.getRol() == Comerciante) && (orden.getEstado() == Confirmada)) {
//                    orden.setEstado(Cancelada);
//                    ordenRepository.save(orden);
//                    return new ResponseEntity<>("La orden se cancelo", HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>("Tu usuario no es administrador, o esta no es una orden confirmada", HttpStatus.CONFLICT);
//                }
//            }else{
//                return new ResponseEntity<>("No existe usuario con el id ingresado.",NOT_FOUND);
//            }
//        }
//    }
//
//    @GetMapping(value = "/usuario/{id_usuario}/orden")
//    public Object obtenerOrdenesDelUsuario(@PathVariable("id_usuario") Long id_usuario){
//        Usuario user = usuarioRepository.findById(id_usuario).orElse(null);
//        if (user == null){
//            return new ResponseEntity<>("El usuario con el id indicado no existe.",HttpStatus.NOT_FOUND);
//        }else{
//            return ordenRepository.findByUsuario(user);
//        }
//    }

}
