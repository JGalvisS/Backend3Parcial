package com.elaparato.elaparato.service;


import com.elaparato.elaparato.model.Producto;
import com.elaparato.elaparato.model.Venta;
import com.elaparato.elaparato.repository.IVentaRepository;
import com.elaparato.elaparato.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private IProductoRepository productoRepository;


    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }


    @Override
    public void saveVenta(Venta vent) {

        List<Producto> productos = vent.getListaProductos();
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            Optional<Producto> managedProducto = productoRepository.findById(producto.getId());
            if (managedProducto.isPresent()) {
                productos.set(i, managedProducto.get());
            } else {
                // Manejar el caso donde el producto no existe, si es necesario
                productoRepository.save(producto);
            }
        }
        vent.setListaProductos(productos);
        ventaRepo.save(vent);
    }

    @Override
    public void deleteVenta(int id) {
        ventaRepo.deleteById(id);
    }

    @Override
    public Venta findVenta(int id) {
        return ventaRepo.findById(id).orElse(null);
    }

    @Override
    public void editVenta(Venta vent) {
        this.saveVenta(vent);
    }

}