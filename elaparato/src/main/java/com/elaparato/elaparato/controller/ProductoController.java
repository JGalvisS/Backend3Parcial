package com.elaparato.elaparato.controller;

import com.elaparato.elaparato.model.Producto;
import com.elaparato.elaparato.service.IProductoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService prodServ;
    @GetMapping
    public String test (){return "estoy funcionando";}

    //crear un nuevo producto
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_repositor', 'ROLE_admin')")
    public String createProducto(@RequestBody Producto prod) {
        prodServ.saveProducto(prod);
        return "Producto creado correctamente";
    }

    //obtener todos los productos
    @GetMapping("/getall")
    @PreAuthorize("hasAnyRole('ROLE_repositor', 'ROLE_admin')")
    public List<Producto> getProductos () {
        return prodServ.getProductos();
    }

    //Modificar los datos de un producto
    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_repositor', 'ROLE_admin')")
    public String editProducto(@RequestBody Producto prod) {
        prodServ.editProducto(prod);
        return "Producto editado correctamente";
    }
}
