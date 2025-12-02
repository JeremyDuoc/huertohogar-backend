package com.tienda.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.backend.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}