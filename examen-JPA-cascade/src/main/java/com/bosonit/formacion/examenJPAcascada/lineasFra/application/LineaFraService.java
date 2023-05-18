package com.bosonit.formacion.examenJPAcascada.lineasFra.application;

import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraInputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraOuputDto;

import java.util.List;

public interface LineaFraService {
    LineaFraOuputDto addLineaFra (LineaFraInputDto lineaFraInputDto);
    LineaFraOuputDto getLineaFraById (Integer id);
    List<LineaFraOuputDto> getAllLineasFra ();
    LineaFraOuputDto updateLineaFra (Integer id,LineaFraInputDto lineaFraInputDto);
    String deleteLineaFraById (Integer id);
}
