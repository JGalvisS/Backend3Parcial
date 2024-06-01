package com.elaparato.elaparato.service;

import com.elaparato.elaparato.model.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getProductos();

    public void saveProducto(Producto prod);

    public void deleteProducto(int id);

    public Producto findProducto(int id);

    public void editProducto(Producto prod);

}