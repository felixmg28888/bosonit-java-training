package com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller.dto;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.domain.CabeceraFra;
import com.bosonit.formacion.examenJPAcascada.cliente.controller.dto.ClienteOutputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraOuputDto;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;


@Getter
public class CabeceraFraOutputDto {

    private Integer idCabeceraFra;
    private Integer idCliente;
    private String nombreCliente;
    private Double importeFra;
    private ClienteOutputDto clienteOutputDto;
    private List<LineaFraOuputDto> lineaFraOuputDtoList = new ArrayList<>();


    public CabeceraFraOutputDto(CabeceraFra cabeceraFra) {
        this.idCabeceraFra = cabeceraFra.getIdCabeceraFra();
        this.idCliente = cabeceraFra.getCliente().getIdCliente();
        this.nombreCliente = cabeceraFra.getCliente().getNombreCliente();
        this.importeFra = cabeceraFra.getImporteFra();
        this.clienteOutputDto = new ClienteOutputDto(cabeceraFra.getCliente());
        this.lineaFraOuputDtoList = cabeceraFra.getLineaFraList().stream().map(LineaFraOuputDto::new).toList();
    }
}
