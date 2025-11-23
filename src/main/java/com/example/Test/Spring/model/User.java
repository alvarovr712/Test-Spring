package com.example.Test.Spring.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.example.Test.Spring.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "usuario")
public class User {

    @Id
    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Role role;
    private String apiKey;
    private List<String> favoriteAgentsIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isEnabled;
}
