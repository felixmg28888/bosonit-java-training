package com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto;

import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteOutputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.domain.LineaFra;
import lombok.Getter;


@Getter
public class LineaFraOuputDto {

    private Integer idLineaFra;
    private String productoNombre;
    private Double cantidad;
    private Double precio;
    private Integer idCabeceraFra;
    private ClienteOutputDto clienteOutputDto;

    public LineaFraOuputDto(LineaFra lineaFra) {
        this.idLineaFra=lineaFra.getIdLineaFra();
        this.productoNombre = lineaFra.getProductoNombre();
        this.cantidad = lineaFra.getCantidad();
        this.precio = lineaFra.getPrecio();
        this.idCabeceraFra=lineaFra.getCabeceraFra().getIdCabeceraFra();
        this.clienteOutputDto=new ClienteOutputDto(lineaFra.getCabeceraFra().getCliente());

    }

}
