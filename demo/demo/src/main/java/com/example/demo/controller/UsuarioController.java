package com.example.demo.controller;
import com.example.demo.entity.Carrito;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController()
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/usuario")
    public Object crearUsuario(@RequestBody Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            return new ResponseEntity<>("Ya existe un usuario registrado con este email.",HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<> (usuarioRepository.save(usuario), OK);
        }
    }

    @GetMapping(value = "/usuario")
    public List<Usuario> verUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/usuario/{id}")
    public Object verUsuario(@PathVariable("id") Long id){
        Usuario usuario =  usuarioRepository.findById(id).orElse(null);
        if (usuario == null){
            return new ResponseEntity<>("El usuario con el id indicado no existe.",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usuario, OK);
        }
    }

    @DeleteMapping(value = "/usuario/{id}")
    public Object borrarUsuario(@PathVariable("id") Long id){
        Usuario usuario =  usuarioRepository.findById(id).orElse(null);
        if (usuario == null){
            return new ResponseEntity<>("No existe usuario con el id ingresado.",NOT_FOUND);
        }else{
            usuarioRepository.delete(usuario);
            return new ResponseEntity<>("El usuario fue eliminado.", OK);
        }
    }

    @PutMapping(value = "/usuario/{id}")
    public Object modificarUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario){
        Usuario user = usuarioRepository.findById(id).orElse(null);
        if (user == null){
            return new ResponseEntity<>("No existe usuario con el id ingresado.",NOT_FOUND);
        }else{
            user.setNombre(usuario.getNombre());
            user.setApellido(usuario.getApellido());
            user.setEmail(usuario.getEmail());
            user.setCiudad(user.getCiudad());
            user.setProvincia(user.getProvincia());
            user.setPais(user.getPais());
            return new ResponseEntity<>(usuarioRepository.save(user), OK);
        }
    }

    @GetMapping(value = "/usuario/ciudad")
    public List<Usuario> buscarUsuariosDeCiudad(@RequestParam String ciudad){
        return usuarioRepository.getByCiudad(ciudad);
    }

    @GetMapping(value = "/usuario/buscar/fechaDesde")
    public List<Usuario> buscarUsuariosPorFechaAltaDede(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") Date fecha){
        return usuarioRepository.getByFechaCreacionAfter(fecha);
    }

}