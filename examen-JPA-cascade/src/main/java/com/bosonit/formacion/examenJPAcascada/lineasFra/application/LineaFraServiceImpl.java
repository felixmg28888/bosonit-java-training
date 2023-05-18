package com.bosonit.formacion.examenJPAcascada.lineasFra.application;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.domain.CabeceraFra;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.repository.CabeceraFraRepository;
import com.bosonit.formacion.examenJPAcascada.exception.EntityNotFoundException;
import com.bosonit.formacion.examenJPAcascada.exception.UnprocessableEntityException;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraInputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraOuputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.domain.LineaFra;
import com.bosonit.formacion.examenJPAcascada.lineasFra.repository.LineaFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaFraServiceImpl implements LineaFraService {

    @Autowired
    LineaFraRepository lineaFraRepository;
    @Autowired
    CabeceraFraRepository cabeceraFraRepository;

    @Override
    public LineaFraOuputDto addLineaFra(LineaFraInputDto lineaFraInputDto) {

        //--------------------Control lineaFraInputDto-------------------------------------------
        if (lineaFraInputDto.getProductoNombre() == null) {
            throw new UnprocessableEntityException(" productoNombre no puede ser nulo.");
        }
        if (lineaFraInputDto.getCantidad() == null) {
            throw new UnprocessableEntityException(" cantidad no puede ser nulo.");
        }
        if (lineaFraInputDto.getPrecio() == null) {
            throw new UnprocessableEntityException(" precio no puede ser nulo.");
        }
        if (lineaFraInputDto.getIdCabeceraFra() == null) {
            throw new UnprocessableEntityException(" idCabeceraFra no puede ser nulo.");
        }
        //--------------------------------------------------------------------------------------


        LineaFra lineaFra = new LineaFra();

        lineaFra.setProductoNombre(lineaFraInputDto.getProductoNombre());
        lineaFra.setCantidad(lineaFraInputDto.getCantidad());
        lineaFra.setPrecio(lineaFraInputDto.getPrecio());


        CabeceraFra cabeceraFra = cabeceraFraRepository.findById(lineaFraInputDto.getIdCabeceraFra()).orElseThrow(() -> new EntityNotFoundException("CabeceraFra no encontrada."));
        lineaFra.setCabeceraFra(cabeceraFra);


        lineaFraRepository.save(lineaFra);

        //--------Importante añado esta lineaFra a la lineaFraList de CabeceraFra para recalcular----
        cabeceraFra.getLineaFraList().add(lineaFra);
        // -----------------Recalcular importe de la factura-----------------------------------------
        double importeFra = cabeceraFra.getLineaFraList().stream()
                .mapToDouble(LineaFra::getPrecio)
                .sum();
        cabeceraFra.setImporteFra(importeFra);

        // Guardar cabeceraFra actualizada en el repositorio
        cabeceraFraRepository.save(cabeceraFra);
        //--------------------------------------------------------------------------------------------------


        return new LineaFraOuputDto(lineaFra);
    }

    @Override
    public LineaFraOuputDto getLineaFraById(Integer id) {
        LineaFra lineaFra = lineaFraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("LineaFra no encontrada."));
        return new LineaFraOuputDto(lineaFra);
    }

    @Override
    public List<LineaFraOuputDto> getAllLineasFra() {
//-------------------------------------Opción 1----------------------------------------
//        List<LineaFraOuputDto> lineaFraOuputDtoList = new ArrayList<>();
//        for (LineaFra l : lineaFraRepository.findAll()) {
//            lineaFraOuputDtoList.add(new LineaFraOuputDto(l));
//        }
//        return lineaFraOuputDtoList;
//    }
//-------------------------------------Opción 2----------------------------------------
        List<LineaFra> lineaFraList = lineaFraRepository.findAll();
        return lineaFraList.stream().map(LineaFraOuputDto::new).toList();
    }
//-------------------------------------------------------------------------------------    }

    @Override
    public LineaFraOuputDto updateLineaFra(Integer id, LineaFraInputDto lineaFraInputDto) {
        LineaFra lineaFra = lineaFraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("LineaFra no encontrada."));

        //--------------------Control lineaFraInputDto-------------------------------------------
        if (lineaFraInputDto.getProductoNombre() != null)
            lineaFra.setProductoNombre(lineaFraInputDto.getProductoNombre());
        if (lineaFraInputDto.getCantidad() != null) lineaFra.setCantidad(lineaFraInputDto.getCantidad());
        if (lineaFraInputDto.getPrecio() != null) lineaFra.setPrecio(lineaFraInputDto.getPrecio());

        //Con este if un poco más rebuscado consigo que si no hay idCabecera en el input del update
        //no salte null pointer Exception
        if (lineaFraInputDto.getIdCabeceraFra() != null) lineaFra.setCabeceraFra(cabeceraFraRepository.
                findById(lineaFraInputDto.getIdCabeceraFra()).orElseThrow(() ->
                        new EntityNotFoundException("CabeceraFra no encontrada.")));
        //--------------------------------------------------------------------------------------

        //Una vez que la linea ha sido actualizada la guardo en el repositorio
        lineaFraRepository.save(lineaFra);


        //Para recalcular el importe de la factura extraigo la cabeceraFra de la lineaFraActualizada

        CabeceraFra cabeceraFra=cabeceraFraRepository.findById(lineaFra.getCabeceraFra().getIdCabeceraFra()).orElseThrow();
        // -----------------Recalcular importe de la factura-----------------------------------------
        double importeFra = cabeceraFra.getLineaFraList().stream()
                .mapToDouble(LineaFra::getPrecio)
                .sum();
        cabeceraFra.setImporteFra(importeFra);

        // Guardar cabeceraFra actualizada en el repositorio
        cabeceraFraRepository.save(cabeceraFra);
        //--------------------------------------------------------------------------------------------------


        return new LineaFraOuputDto(lineaFra);
    }

    @Override
    public String deleteLineaFraById(Integer id) {
        LineaFra lineaFra = lineaFraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("LineaFra no encontrada."));


        //Actualización de la lineaFraList de la cabeceraFra de la lineaFraActualizada
        //Y recálculo del importe de la cabeceraFra

        CabeceraFra cabeceraFra = lineaFra.getCabeceraFra();
        // Extraigao la lista de líneas de factura de la cabecera
        List<LineaFra> lineaFraList = cabeceraFra.getLineaFraList();
        // Elimino la líneaFra eliminada de la lista
        lineaFraList.remove(lineaFra);

        //Recalculo el nuevo importe de la lista
        double importeFra = lineaFraList.stream()
                .mapToDouble(LineaFra::getPrecio)
                .sum();
        //Y lo seteo en la cabecera
        cabeceraFra.setImporteFra(importeFra);

        //Guardo la cabeceraFra actualizada
        cabeceraFraRepository.save(cabeceraFra);



        lineaFraRepository.delete(lineaFra);

        return "La lineaFra con id " + id + " ha sido borrada.";
    }
}
