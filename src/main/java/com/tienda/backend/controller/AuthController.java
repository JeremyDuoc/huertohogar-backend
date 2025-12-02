package com.tienda.backend.controller;

import com.tienda.backend.model.Usuario;
import com.tienda.backend.repository.UsuarioRepository;
import com.tienda.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El email ya está registrado");
        }
        
    
        if (usuario.getRole() == null || usuario.getRole().isEmpty()) {
            usuario.setRole("USER");
        }
        
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuarioDb = usuarioOpt.get();
            if (usuarioDb.getPassword().equals(loginRequest.getPassword())) {
                
        
                String token = jwtUtil.generateToken(usuarioDb.getEmail(), usuarioDb.getRole());
                
            
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("role", usuarioDb.getRole());
                response.put("email", usuarioDb.getEmail());
                response.put("nombre", usuarioDb.getNombre());
                
                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}