package com.bosonit.formacion.block17springbatch.steps.step1;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.PathResource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TiempoCsvReader extends FlatFileItemReader<Tiempo> {

    public TiempoCsvReader() {
        this.setResource(new PathResource("src/main/resources/entradaOriginal/entrada.csv"));
        this.setLineMapper((line, lineNumber) -> {
            String[] fields = line.split(",");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = dateFormat.parse(fields[1]);

            Tiempo tiempo = new Tiempo();
            tiempo.setLocalidad(fields[0]);
            tiempo.setFecha(fecha);
            tiempo.setTemperatura(Integer.parseInt(fields[2]));

            return tiempo;
        });
    }
}
