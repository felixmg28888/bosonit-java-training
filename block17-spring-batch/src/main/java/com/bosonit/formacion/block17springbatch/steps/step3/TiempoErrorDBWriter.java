package com.bosonit.formacion.block17springbatch.steps.step3;

import com.bosonit.formacion.block17springbatch.domain.TiempoError;
import com.bosonit.formacion.block17springbatch.repository.TiempoErrorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


public class TiempoErrorDBWriter implements ItemWriter<TiempoError> {
    @Autowired
    private TiempoErrorRepository tiempoErrorRepository;

    @Override
    public void write(List<? extends TiempoError> tiempoErrorList) throws Exception {
        tiempoErrorRepository.saveAll(tiempoErrorList);
    }
}