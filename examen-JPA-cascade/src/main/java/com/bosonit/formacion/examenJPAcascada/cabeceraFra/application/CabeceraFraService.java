package com.bosonit.formacion.examenJPAcascada.cabeceraFra.application;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller.dto.CabeceraFraInputDto;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller.dto.CabeceraFraOutputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraInputDto;

import java.util.List;

public interface CabeceraFraService {

    CabeceraFraOutputDto addCabeceraFra (CabeceraFraInputDto cabeceraFraInputDto);
    CabeceraFraOutputDto getCabeceraFraById (Integer id);
    List<CabeceraFraOutputDto> getAllCabeceraFra ();
    CabeceraFraOutputDto updateCabeceraFra (Integer id, CabeceraFraInputDto cabeceraFraInputDto);
    String deleteCabeceraFraById (Integer id);

    CabeceraFraOutputDto addLineaFraToCabeceraFra (LineaFraInputDto lineaFraInputDto);

}
