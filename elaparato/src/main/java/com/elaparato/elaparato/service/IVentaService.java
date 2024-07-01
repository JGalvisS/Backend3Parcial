package com.elaparato.elaparato.service;

import com.elaparato.elaparato.model.Venta;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IVentaService {

    public List<Venta> getVentas();


    //Transactional asegura que todas las operaciones dentro del método son exitosas, la transacción se compromete; si alguna falla, la transacción se revierte
    @Transactional
    void saveVenta(Venta vent);

    //acá en la implementación se puede hacer por ejemplo borrado lógico
    public void deleteVenta(int id);


    public Venta findVenta(int id);

    public void editVenta(Venta vent);

}