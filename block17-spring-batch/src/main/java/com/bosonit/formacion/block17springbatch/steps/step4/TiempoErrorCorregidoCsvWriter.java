package com.bosonit.formacion.block17springbatch.steps.step4;

import com.bosonit.formacion.block17springbatch.domain.TiempoErrorCorregido;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.util.List;


public class TiempoErrorCorregidoCsvWriter implements ItemWriter<TiempoErrorCorregido> {
    @Override
    public void write(List<? extends TiempoErrorCorregido> list) throws Exception {
        FileWriter fileWriter = new FileWriter("src/main/resources/generatedCsv/" +
                "04tiempoErrorCorregido.csv", true);

        for (TiempoErrorCorregido tiempoErrorCorregido : list) {
            fileWriter.append(tiempoErrorCorregido.getLocalidad());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoErrorCorregido.getFecha()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoErrorCorregido.getTemperatura()));
            fileWriter.append("\n");

        }
        fileWriter.flush();
        fileWriter.close();
    }
}