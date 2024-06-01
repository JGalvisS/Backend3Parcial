package com.elaparato.elaparato.repository;

import com.elaparato.elaparato.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
}
