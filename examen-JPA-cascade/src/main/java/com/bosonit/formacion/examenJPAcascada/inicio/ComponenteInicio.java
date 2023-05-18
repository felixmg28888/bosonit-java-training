package com.bosonit.formacion.examenJPAcascada.inicio;

import com.bosonit.formacion.examenJPAcascada.cabeceraFra.domain.CabeceraFra;
import com.bosonit.formacion.examenJPAcascada.cabeceraFra.repository.CabeceraFraRepository;
import com.bosonit.formacion.examenJPAcascada.cliente.domain.Cliente;
import com.bosonit.formacion.examenJPAcascada.cliente.repository.ClienteRepository;
import com.bosonit.formacion.examenJPAcascada.lineasFra.domain.LineaFra;
import com.bosonit.formacion.examenJPAcascada.lineasFra.repository.LineaFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComponenteInicio implements CommandLineRunner {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    CabeceraFraRepository cabeceraFraRepository;

    @Autowired
    LineaFraRepository lineaFraRepository;

    @Override
    public void run(String... args) throws Exception {

        Cliente cliente = new Cliente();
        cliente.setNombreCliente("Cliente1");
        clienteRepository.save(cliente);


        CabeceraFra cabeceraFra = new CabeceraFra();
        cabeceraFra.setCliente(cliente);


        LineaFra lineaFra1 = new LineaFra();
        lineaFra1.setProductoNombre("Producto1");
        lineaFra1.setCantidad(20.0);
        lineaFra1.setPrecio(200.0);
        lineaFra1.setCabeceraFra(cabeceraFra);

        LineaFra lineaFra2 = new LineaFra();
        lineaFra2.setProductoNombre("Producto2");
        lineaFra2.setCantidad(50.0);
        lineaFra2.setPrecio(500.0);
        lineaFra2.setCabeceraFra(cabeceraFra);


        List<LineaFra> lineaFraList = new ArrayList<>();
        lineaFraList.add(lineaFra1);
        lineaFraList.add(lineaFra2);

        cabeceraFra.setLineaFraList(lineaFraList);

        //------------------Cálculo del importe de la cabeceraFra-----------------------------------------------
        //Bucle for each donde creo una lista de lineaFras que tiene la cabeceraFra
        //Y creo una variable para calcular el importe del precio de todas las lienasFras obtenidas
        //Para posteriormente setear el importe de la factura.
        double importeFra = 0;
        for (LineaFra lineaFra : cabeceraFra.getLineaFraList()) {
            importeFra += lineaFra.getPrecio();
        }
        cabeceraFra.setImporteFra(importeFra);
        //-------------------------------------------------------------------------------------------------------

        cabeceraFraRepository.save(cabeceraFra);

    }

}
