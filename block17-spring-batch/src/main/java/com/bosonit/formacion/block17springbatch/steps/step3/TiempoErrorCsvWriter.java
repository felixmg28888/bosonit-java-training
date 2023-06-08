package com.bosonit.formacion.block17springbatch.steps.step3;

import com.bosonit.formacion.block17springbatch.domain.TiempoError;
import org.springframework.batch.item.ItemWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TiempoErrorCsvWriter implements ItemWriter<TiempoError> {

    @Override
    public void write(List<? extends TiempoError> list) throws IOException {

        FileWriter fileWriter = new FileWriter( "src/main/resources/generatedCsv/03tiempoError.csv", true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (TiempoError tiempoError : list) {
            fileWriter.append(tiempoError.getLocalidad());
            fileWriter.append(",");
            fileWriter.append(dateFormat.format(tiempoError.getFecha()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(tiempoError.getTemperatura()));
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }



    }
