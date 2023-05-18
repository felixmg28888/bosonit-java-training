package com.bosonit.formacion.examenJPAcascada.cabeceraFra.application;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller.dto.CabeceraFraInputDto;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.controller.dto.CabeceraFraOutputDto;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.domain.CabeceraFra;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.repository.CabeceraFraRepository;
import com.bosonit.formacion.examenJPAcascada.cliente.repository.ClienteRepository;
import com.bosonit.formacion.examenJPAcascada.exception.EntityNotFoundException;
import com.bosonit.formacion.examenJPAcascada.exception.UnprocessableEntityException;
import com.bosonit.formacion.examenJPAcascada.lineasFra.controller.dto.LineaFraInputDto;
import com.bosonit.formacion.examenJPAcascada.lineasFra.domain.LineaFra;
import com.bosonit.formacion.examenJPAcascada.lineasFra.repository.LineaFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabeceraFraServiceImpl implements CabeceraFraService {

    @Autowired
    CabeceraFraRepository cabeceraFraRepository;

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    private LineaFraRepository lineaFraRepository;

    @Override
    public CabeceraFraOutputDto addCabeceraFra(CabeceraFraInputDto cabeceraFraInputDto) {

        //--------------------Control cabeceraFraInputDto----------------------------------------

        if (cabeceraFraInputDto.getIdCliente() == null) {
            throw new UnprocessableEntityException(" idCliente no puede ser nulo.");
        }
        //--------------------------------------------------------------------------------------

        CabeceraFra cabeceraFra = new CabeceraFra();


        cabeceraFra.setCliente(clienteRepository.findById(cabeceraFraInputDto.getIdCliente()).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado.")));


        cabeceraFraRepository.save(cabeceraFra);
        return new CabeceraFraOutputDto(cabeceraFra);
    }

    @Override
    public CabeceraFraOutputDto getCabeceraFraById(Integer id) {
        CabeceraFra cabeceraFra = cabeceraFraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CabeceraFra no encontrada."));
        return new CabeceraFraOutputDto(cabeceraFra);
    }

    @Override
    public List<CabeceraFraOutputDto> getAllCabeceraFra() {
//-------------------------------------Opción 1----------------------------------------
//        List<CabeceraFraOutputDto> cabeceraFraOutputDtoList = new ArrayList<>();
//        for (CabeceraFra c : cabeceraFraRepository.findAll()) {
//            cabeceraFraOutputDtoList.add(new CabeceraFraOutputDto(c));
//        }
//        return cabeceraFraOutputDtoList;
//    }
//-------------------------------------Opción 2----------------------------------------
        List<CabeceraFra> cabeceraFraList = cabeceraFraRepository.findAll();
        return cabeceraFraList.stream().map(CabeceraFraOutputDto::new).toList();
    }
//-------------------------------------------------------------------------------------


    @Override
    public CabeceraFraOutputDto updateCabeceraFra(Integer id, CabeceraFraInputDto cabeceraFraInputDto) {
        CabeceraFra cabeceraFra = cabeceraFraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CabeceraFra no encontrada."));

        if (cabeceraFraInputDto.getIdCliente() != null)
            cabeceraFra.setCliente(clienteRepository.findById(cabeceraFraInputDto.getIdCliente()).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado.")));


        cabeceraFraRepository.save(cabeceraFra);
        return new CabeceraFraOutputDto(cabeceraFra);

    }

    @Override
    public String deleteCabeceraFraById(Integer id) {
        CabeceraFra cabeceraFra = cabeceraFraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CabeceraFra no encontrada."));
        cabeceraFraRepository.delete(cabeceraFra);
        return "La cabeceraFra con id " + id + " ha sido borrada.";
    }

    @Override
    public CabeceraFraOutputDto addLineaFraToCabeceraFra(LineaFraInputDto lineaFraInputDto) {
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


        return new CabeceraFraOutputDto(cabeceraFra);

    }
}
