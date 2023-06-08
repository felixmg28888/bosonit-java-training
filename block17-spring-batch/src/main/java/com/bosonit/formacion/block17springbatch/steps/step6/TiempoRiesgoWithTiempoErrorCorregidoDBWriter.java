package com.bosonit.formacion.block17springbatch.steps.step6;

import com.bosonit.formacion.block17springbatch.domain.TiempoRiesgo;
import com.bosonit.formacion.block17springbatch.repository.TiempoRiesgoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TiempoRiesgoWithTiempoErrorCorregidoDBWriter implements ItemWriter<TiempoRiesgo> {
    @Autowired
    private TiempoRiesgoRepository tiempoRiesgoRepository;

    @Override
    public void write(List<? extends TiempoRiesgo> tiempoRiesgoList) throws Exception {
        tiempoRiesgoRepository.saveAll(tiempoRiesgoList);
    }
}