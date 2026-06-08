package com.jpa_sesion04.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @Column(name = "idcategoria", nullable = false)
    private Integer idcategoria;

    @Column(name = "descripcion", nullable = false, length = 45)
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;

    @Override
    public String toString() {
        return idcategoria + " - " + descripcion;
    }
}
