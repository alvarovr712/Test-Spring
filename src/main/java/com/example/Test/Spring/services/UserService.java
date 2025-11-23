package com.example.Test.Spring.services;

import com.example.Test.Spring.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface UserService {

    Mono<Map<String, Object>> crearUsuarioCompleto(Map<String,Object> userData);
    Flux<User> obtenerTodosLosUsuarios();
    Flux<User> obtenerTodosLosUsuariosIncluirDeshabilitados();
    Mono<User> obtenerUsuarioPorId(String id);
}
