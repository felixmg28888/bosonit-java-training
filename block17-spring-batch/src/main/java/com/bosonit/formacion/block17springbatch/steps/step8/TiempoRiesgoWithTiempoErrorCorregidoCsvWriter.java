package com.bosonit.formacion.block17springbatch.steps.step8;

import com.bosonit.formacion.block17springbatch.domain.TiempoRiesgo;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.util.List;


public class TiempoRiesgoWithTiempoErrorCorregidoCsvWriter implements ItemWriter<TiempoRiesgo> {
    @Override
    public void write(List<? extends TiempoRiesgo> list) throws Exception {
        FileWriter fileWriter = new FileWriter("src/main/resources/generatedCsv/" +
                "06tiempoRiesgoWithErroresCorregidos.csv", true);

        for (TiempoRiesgo tiempoRiesgo : list) {
            fileWriter.append(tiempoRiesgo.getLocalidad());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoRiesgo.getMes()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoRiesgo.getAgno()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoRiesgo.getTemperatura()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoRiesgo.getRiesgo()));
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}