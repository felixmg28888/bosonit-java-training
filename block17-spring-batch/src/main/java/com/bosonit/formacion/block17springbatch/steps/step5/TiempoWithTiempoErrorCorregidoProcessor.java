package com.bosonit.formacion.block17springbatch.steps.step5;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import com.bosonit.formacion.block17springbatch.domain.TiempoErrorCorregido;
import org.springframework.batch.item.ItemProcessor;

public class TiempoWithTiempoErrorCorregidoProcessor implements ItemProcessor<TiempoErrorCorregido, Tiempo> {

    @Override
    public Tiempo process(TiempoErrorCorregido tiempoErrorCorregido) throws Exception {
        Tiempo tiempo = new Tiempo();
        tiempo.setLocalidad(tiempoErrorCorregido.getLocalidad());
        tiempo.setFecha(tiempoErrorCorregido.getFecha());
        tiempo.setTemperatura(tiempoErrorCorregido.getTemperatura());


        return tiempo;
    }
}
