package com.elaparato.elaparato.controller;


import com.elaparato.elaparato.model.Venta;
import com.elaparato.elaparato.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ventas")
public class VentaController {


    @Autowired
    private IVentaService ventServ;

    //crear una nueva venta
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('vendedor', 'admin')")
    public ResponseEntity createVenta(@RequestBody Venta vent) {
        ventServ.saveVenta(vent);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venta creada correctamente");
    }

    //obtener todas las ventas
    @GetMapping("/getall")
    @PreAuthorize("hasAnyRole('ROLE_vendedor', 'ROLE_admin')")
    public List<Venta> getVentas () {
        return ventServ.getVentas();
    }

    //Modificar los datos de una venta
    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_vendedor', 'ROLE_admin')")
    public String editVenta(@RequestBody Venta vent) {
        ventServ.editVenta(vent);
        return "Venta editada correctamente";
    }


}
