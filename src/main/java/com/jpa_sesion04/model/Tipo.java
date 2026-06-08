package com.jpa_sesion04.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_tipos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "usuarios")
public class Tipo {

    @Id
    @Column(name = "idtipo", nullable = false)
    private Integer idtipo;

    @Column(name = "descripcion", nullable = false, length = 15)
    private String descripcion;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}
