package com.bosonit.formacion.examenJPAcascada.cabeceraFra.domain;

import com.bosonit.formacion.examenJPAcascada.cliente.domain.Cliente;
import com.bosonit.formacion.examenJPAcascada.lineasFra.domain.LineaFra;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class CabeceraFra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCabecera", unique = true)
    private Integer idCabeceraFra;

    @Column(name = "importeFra")
    private Double importeFra;

    //------------------------Relaciones------------------------
    @ManyToOne
    @JoinColumn(name="idCliente")
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cabeceraFra",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaFra> lineaFraList=new ArrayList<>();

    //----------------------------------------------------------

}
