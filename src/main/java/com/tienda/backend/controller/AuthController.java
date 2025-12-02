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
@CrossOrigin(origins = "http://localhost:3000") // Asegúrate que sea el puerto de tu Frontend
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTRO
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El email ya está registrado");
        }
        
        // Asignar rol por defecto si no viene
        if (usuario.getRole() == null || usuario.getRole().isEmpty()) {
            usuario.setRole("USER");
        }
        
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        // Validación simple (En producción usaríamos BCrypt para la password)
        if (usuarioOpt.isPresent()) {
            Usuario usuarioDb = usuarioOpt.get();
            if (usuarioDb.getPassword().equals(loginRequest.getPassword())) {
                
                // ¡Credenciales correctas! Generamos el token
                String token = jwtUtil.generateToken(usuarioDb.getEmail(), usuarioDb.getRole());
                
                // Devolvemos el token y los datos del usuario al Frontend
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