package com.bosonit.formacion.block0603pathvariableheaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
@RestController
public class Controlador {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @PostMapping("/user")
    public Usuario creaUsuario(@RequestBody Usuario usu){
        Usuario usuario=servicioUsuario.creaUsuario(usu.getId(), usu.getNombre());
        return usuario;
    }

    @GetMapping("/user/{id}")
    public int muestraId(@PathVariable int id){
        return id;
    }

    @PutMapping("/post")
    public HashMap creaHashMap (@RequestParam(name= "var1") String var1,
                          @RequestParam(name= "var2") String var2){
        return servicioUsuario.creaHashMap(var1, var2);
    }
}

