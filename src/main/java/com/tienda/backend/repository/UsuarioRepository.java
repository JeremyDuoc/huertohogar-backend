package com.tienda.backend.repository;

import com.tienda.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Este método nos permite buscar un usuario por su correo
    Optional<Usuario> findByEmail(String email);
}