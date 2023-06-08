package com.bosonit.formacion.block17springbatch.steps.step5;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import com.bosonit.formacion.block17springbatch.repository.TiempoErrorCorregidoRepository;
import com.bosonit.formacion.block17springbatch.repository.TiempoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TiempoWithTiempoErrorCorregidoDBWriter implements ItemWriter<Tiempo> {

    @Autowired
    private TiempoRepository tiempoRepository;

    @Autowired
    private TiempoErrorCorregidoRepository tiempoErrorCorregidoRepository;

    @Override
    public void write(List<? extends Tiempo> tiempoList) throws Exception {
        tiempoRepository.saveAll(tiempoList);
    }
}
