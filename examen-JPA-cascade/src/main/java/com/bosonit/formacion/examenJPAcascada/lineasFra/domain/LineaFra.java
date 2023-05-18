package com.bosonit.formacion.examenJPAcascada.lineasFra.domain;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.domain.CabeceraFra;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table
public class LineaFra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLineaFra", unique = true)
    private Integer idLineaFra;
    @Column(name="proNomb", nullable = false)
    private String productoNombre;
    private Double cantidad;
    private Double precio;

    //------------------------Relaciones------------------------
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCabecera")
    private CabeceraFra cabeceraFra;

    //----------------------------------------------------------
}
