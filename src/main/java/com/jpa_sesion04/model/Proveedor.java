package com.jpa_sesion04.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_proveedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @Column(name = "idproveedor", nullable = false)
    private Integer idproveedor;

    @Column(name = "nombre_rs", nullable = false, length = 45)
    private String nombreRs;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "email", length = 45)
    private String email;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;

    @Override
    public String toString() {
        return idproveedor + " - " + nombreRs;
    }
}
