package com.elaparato.elaparato.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    @SequenceGenerator(name = "venta_seq", sequenceName = "venta_seq", allocationSize = 1)
    private int id_venta;
    private Date fecha;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "producto_lista_ventas",
            joinColumns = @JoinColumn(name = "lista_ventas_id_venta"),
            inverseJoinColumns = @JoinColumn(name = "lista_productos_id")
    )
    private List<Producto> listaProductos;

    public Venta() {}

    public Venta(int id_venta, Date fecha, List<Producto> listaProductos) {
        this.id_venta = id_venta;
        this.fecha = fecha;
        this.listaProductos = listaProductos;
    }
}
