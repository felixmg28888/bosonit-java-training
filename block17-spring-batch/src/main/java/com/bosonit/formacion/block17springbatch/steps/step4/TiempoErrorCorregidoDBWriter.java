package com.bosonit.formacion.block17springbatch.steps.step4;

import com.bosonit.formacion.block17springbatch.domain.TiempoErrorCorregido;
import com.bosonit.formacion.block17springbatch.repository.TiempoErrorCorregidoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TiempoErrorCorregidoDBWriter implements ItemWriter<TiempoErrorCorregido> {
    @Autowired
    private TiempoErrorCorregidoRepository tiempoErrorCorregidoRepository;

    @Override
    public void write(List<? extends TiempoErrorCorregido> tiempoErrorCorregidoList) throws Exception {
        tiempoErrorCorregidoRepository.saveAll(tiempoErrorCorregidoList);
    }
}