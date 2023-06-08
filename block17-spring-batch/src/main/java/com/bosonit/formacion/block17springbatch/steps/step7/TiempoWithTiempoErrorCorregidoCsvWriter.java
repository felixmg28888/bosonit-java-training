package com.bosonit.formacion.block17springbatch.steps.step7;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.util.List;


public class TiempoWithTiempoErrorCorregidoCsvWriter implements ItemWriter<Tiempo> {
    @Override
    public void write(List<? extends Tiempo> list) throws Exception {
        FileWriter fileWriter = new FileWriter("src/main/resources/generatedCsv/05tiempoWithTiempoErrorCorregido.csv", true);

        for (Tiempo tiempo : list) {
            fileWriter.append(tiempo.getLocalidad());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempo.getFecha()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempo.getTemperatura()));
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}