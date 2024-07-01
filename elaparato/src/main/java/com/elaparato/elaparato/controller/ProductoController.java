package com.elaparato.elaparato.controller;

import com.elaparato.elaparato.model.Producto;
import com.elaparato.elaparato.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService prodServ;
    @GetMapping
    public String test (){return "estoy funcionando";}


    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('repositor', 'admin')")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto prod) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prodServ.saveProducto(prod));
    }

    //obtener todos los productos
    @GetMapping("/getall")
    @PreAuthorize("hasAnyRole('repositor', 'admin')")
    public List<Producto> getProductos () {
        //return prodServ.getProductos();
        return ResponseEntity.ok(prodServ.getProductos()).getBody();
    }

    //Modificar los datos de un producto
    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('repositor', 'admin')")
    public String editProducto(@RequestBody Producto prod) {
         prodServ.editProducto(prod);
        return "Producto editado correctamente";

    }
}
