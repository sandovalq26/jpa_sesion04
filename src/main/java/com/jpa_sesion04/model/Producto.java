package com.jpa_sesion04.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {

    @Id
    @Column(name = "id_prod", nullable = false, length = 5)
    private String idProd;

    @Column(name = "des_prod", nullable = false, length = 45)
    private String desProd;

    @Column(name = "stk_prod", nullable = false)
    private Integer stkProd = 0;

    @Column(name = "pre_prod", nullable = false, precision = 8, scale = 2)
    private BigDecimal preProd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcategoria", nullable = false,
                foreignKey = @ForeignKey(name = "fk_prod_categoria"))
    private Categoria categoria;

    @Column(name = "est_prod", nullable = false)
    private Boolean estProd = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idproveedor", nullable = false,
                foreignKey = @ForeignKey(name = "fk_prod_proveedor"))
    private Proveedor proveedor;
}
