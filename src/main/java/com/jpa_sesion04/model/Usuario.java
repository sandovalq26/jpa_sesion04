package com.jpa_sesion04.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    @Id
    @Column(name = "cod_usua", nullable = false)
    private Integer codUsua;

    @Column(name = "nom_usua", nullable = false, length = 15)
    private String nomUsua;

    @Column(name = "ape_usua", nullable = false, length = 25)
    private String apeUsua;

    @Column(name = "usr_usua", nullable = false, length = 45, unique = true)
    private String usrUsua;

    @Column(name = "cla_usua", nullable = false, length = 100)
    private String claUsua;

    @Column(name = "fna_usua")
    private LocalDate fnaUsua;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipo", nullable = false,
                foreignKey = @ForeignKey(name = "fk_usua_tipo"))
    private Tipo tipo;

    @Column(name = "est_usua", nullable = false)
    private Integer estUsua = 1;
}
