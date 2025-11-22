package com.example.Test.Spring.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "empresa")
public class empresa {
    @Id
    private int id;
    private String nombre;

}
