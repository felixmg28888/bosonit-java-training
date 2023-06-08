package com.bosonit.formacion.block17springbatch.steps.step6;

import com.bosonit.formacion.block17springbatch.domain.TiempoErrorCorregido;
import com.bosonit.formacion.block17springbatch.domain.TiempoRiesgo;
import org.springframework.batch.item.ItemProcessor;

import java.util.Calendar;

public class TiempoRiesgoWithTiempoErrorCorregidoProcessor implements ItemProcessor<TiempoErrorCorregido, TiempoRiesgo> {

    @Override
    public TiempoRiesgo process(TiempoErrorCorregido tiempoErrorCorregido) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempoErrorCorregido.getFecha());

        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setLocalidad(tiempoErrorCorregido.getLocalidad());
        tiempoRiesgo.setMes(calendar.get(Calendar.MONTH));
        tiempoRiesgo.setAgno(calendar.get(Calendar.YEAR));
        tiempoRiesgo.setTemperatura(tiempoErrorCorregido.getTemperatura());
        tiempoRiesgo.setRiesgo(tiempoErrorCorregido.getTemperatura() >= 36 ? "HIGH" :
                (tiempoErrorCorregido.getTemperatura() < 36 && tiempoErrorCorregido.getTemperatura() >= 32) ? "NORMAL" : "LOW");
        tiempoRiesgo.setTiempo(tiempoErrorCorregido.getTiempo());
        tiempoRiesgo.setTemperatura(tiempoErrorCorregido.getTemperatura());


        return tiempoRiesgo;
    }
}





