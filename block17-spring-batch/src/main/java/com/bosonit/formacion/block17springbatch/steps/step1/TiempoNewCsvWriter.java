package com.bosonit.formacion.block17springbatch.steps.step1;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TiempoNewCsvWriter implements ItemWriter<Tiempo> {

    @Override
    public void write(List<? extends Tiempo> list) throws IOException {

        FileWriter fileWriter = new FileWriter( "src/main/resources/generatedCsv/01entradaConErrores.csv", true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Tiempo tiempo : list) {
            fileWriter.append(tiempo.getLocalidad());
            fileWriter.append(",");
            fileWriter.append(dateFormat.format(tiempo.getFecha()));
            fileWriter.append(",");
            if (tiempo.getTemperatura() > 50 || tiempo.getTemperatura() < -20) {
                fileWriter.append(tiempo.getTemperatura() + " -- ERROR");
            } else {
                fileWriter.append(String.valueOf(tiempo.getTemperatura()));
            }
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}