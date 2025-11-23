package com.example.Test.Spring.services.impl;

import com.example.Test.Spring.enums.Role;
import com.example.Test.Spring.model.User;
import com.example.Test.Spring.repositories.UserRepository;
import com.example.Test.Spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Mono<Map<String, Object>> crearUsuarioCompleto(Map<String, Object> userData) {
        String email = Objects.toString(userData.get("email"), "");

        return userRepository.findByEmail(email)
                .flatMap(existing -> {
                    // Si ya existe, devolvemos un error
                    Map<String, Object> res = new HashMap<>();
                    res.put("error", "Ya existe un usuario con ese email");
                    return Mono.just(res);
                })
                .switchIfEmpty(
                        // Si no existe, creamos el usuario
                        Mono.defer(() -> {
                            User usuario = new User();
                            usuario.setUsername(Objects.toString(userData.get("username"), ""));
                            usuario.setName(Objects.toString(userData.get("name"), ""));
                            usuario.setSurname(Objects.toString(userData.get("surname"), ""));
                            usuario.setEmail(email);
                            usuario.setRole(Role.USER);
                            usuario.setApiKey("");
                            usuario.setFavoriteAgentsIds(Collections.emptyList());
                            usuario.setCreatedAt(LocalDateTime.now());
                            usuario.setUpdatedAt(null);
                            usuario.setIsEnabled(false);

                            return userRepository.save(usuario)
                                    .map(saved -> {
                                        Map<String, Object> res = new HashMap<>();
                                        res.put("usuario", saved);
                                        res.put("message", "Usuario creado. Acceso vía credenciales corporativas Office365.");
                                        return res;
                                    });
                        })
                );
    }

    @Override
    public Flux<User> obtenerTodosLosUsuarios() {
        return userRepository.findByIsEnabledTrue();
    }

    @Override
    public Flux<User> obtenerTodosLosUsuariosIncluirDeshabilitados() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> obtenerUsuarioPorId(String id) {
        return userRepository.findById(id);
    }
}
