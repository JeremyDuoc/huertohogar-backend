package com.tienda.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data 
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    @Column(unique = true) 
    private String email;
    
    private String password;
    
    private String role; 
}