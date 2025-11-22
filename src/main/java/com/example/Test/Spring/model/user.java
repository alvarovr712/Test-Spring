package com.example.Test.Spring.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class user {

    private String username;
}
