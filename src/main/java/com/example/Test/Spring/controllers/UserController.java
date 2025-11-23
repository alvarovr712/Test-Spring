package com.example.Test.Spring.controllers;

import com.example.Test.Spring.model.User;
import com.example.Test.Spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/crear")
    public Mono<Map<String, Object>> crearUsuario(@RequestBody Map<String, Object> userData) {
        return userService.crearUsuarioCompleto(userData);
    }
    @GetMapping
    public ResponseEntity<List<User>> obtenerTodosLosUsuarios(){
        try{
            List<User> users = userService.obtenerTodosLosUsuarios().collectList().block();
            return ResponseEntity.ok(users != null ? users : List.of());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
    @GetMapping("/todos")
    public  ResponseEntity<List<User>> obtenerTodosLosUsuariosIncluirDeshabilitados(){
        try{
            List<User> users = userService.obtenerTodosLosUsuariosIncluirDeshabilitados().collectList().block();
            return ResponseEntity.ok(users != null ? users : List.of());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable String id){
        try{
            User user = userService.obtenerUsuarioPorId(id).block();
            if(user != null){
                return ResponseEntity.ok(user);
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
