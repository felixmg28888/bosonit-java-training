package com.bosonit.formacion.block17springbatch.steps.step1;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import com.bosonit.formacion.block17springbatch.repository.TiempoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TiempoDBWriter implements ItemWriter<Tiempo> {

    @Autowired
    private TiempoRepository tiempoRepository;

    @Override
    public void write(List<? extends Tiempo> list) throws Exception {
        tiempoRepository.saveAll(list);
    }

}

