package com.example.Test.Spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "agentes")
public class agent {
    private int id;
    private String name;
}
