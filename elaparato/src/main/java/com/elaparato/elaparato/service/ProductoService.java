package com.elaparato.elaparato.service;

import com.elaparato.elaparato.model.Producto;
import com.elaparato.elaparato.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoRepository prodRepo;

    @Override
    public List<Producto> getProductos() {
        return prodRepo.findAll();
    }

    @Override
    public Producto saveProducto(Producto prod) {

        return prodRepo.save(prod);
    }

    @Override
    public void deleteProducto(int id) {
        prodRepo.deleteById(id);
    }

    @Override
    public Producto findProducto(int id) {

        return prodRepo.findById(id).orElse(null);
    }

    @Override
    public void editProducto(Producto prod) {
         this.saveProducto(prod);
    }

}