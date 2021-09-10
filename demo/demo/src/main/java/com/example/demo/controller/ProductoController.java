package com.example.demo.controller;


import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

@RestController()
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

//    @Autowired
//    private DetalleRepository detalleRepository;

    //
//    @Autowired
//    private OrdenRepository ordenRepository;
//
//    @Autowired
//    private LineaRepository lineaRepository;
//

    @PostMapping(value = "/producto")
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @GetMapping(value = "/producto")
    public List<Producto> verProductos(){
        return  productoRepository.findAll();
    }

    @GetMapping(value = "/producto/{id_producto}")
    public Object verProducto(@PathVariable("id_producto") Long id_producto){
        Producto producto = productoRepository.findById(id_producto).orElse(null);
        if (producto == null){
            return new ResponseEntity<>("El producto con el id indicado no existe.", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(producto, OK);
        }
    }

    @DeleteMapping(value = "producto/{id}")
    public Object borrarProducto(@PathVariable("id") Long id){
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null){
            productoRepository.delete(producto);
            return new ResponseEntity<>("El producto fue eliminado.", OK);
        }else{
            return new ResponseEntity<>("El producto con el id indicado no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/producto/{id_producto}")
    public Object modificarProducto(@PathVariable("id_producto") Long id_producto, @RequestBody  Producto producto_modificado){
        Producto producto = productoRepository.findById(id_producto).orElse(null);
        if (producto != null){
            producto.setDescripcion(producto_modificado.getDescripcion());
            producto.setNombre(producto_modificado.getNombre());
            producto.setPrecio_unitario(producto_modificado.getPrecio_unitario());
            producto.setContenido(producto_modificado.getContenido());
            producto.setPublicado(producto_modificado.getPublicado());
            productoRepository.save(producto);
            return new ResponseEntity<>(producto, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("El producto con el id indicado no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "producto/buscarContiene")
    public List<Producto> buscarProductoPorCadenaContenidaEnNombre(@RequestParam String nombre){
        return productoRepository.findByNombreContaining(nombre);
    }

    @GetMapping(value = "producto/noPublicado")
    public List<Producto> buscarProductoNoPublicados(){
        return productoRepository.findByPublicadoFalse();
    }
//
//    @GetMapping(value = "/producto/buscarComienzo")
//    public List<Producto> buscarPorNombreComienzaCon(@RequestParam(value="nombre") String nombre){
//        return productoRepository.findByNombreStartingWith(nombre);
//    }
//
//
//
//    @GetMapping(value = "/producto/buscarCategoria")
//    public List<Producto> buscarPorCategoria(@RequestParam(value="nombre") Categoria nombre){
//        return productoRepository.findByCategoria(nombre);
//    }
//
//
//
//
//
//    @GetMapping(value = "producto/siPublicado")
//    public List<Producto> buscarProductoSiPublicados(){
//        return productoRepository.findByPublicadoTrue();
//    }
}
