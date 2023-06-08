package com.bosonit.formacion.block17springbatch.steps.step4;

import com.bosonit.formacion.block17springbatch.domain.TiempoError;
import com.bosonit.formacion.block17springbatch.domain.TiempoErrorCorregido;
import org.springframework.batch.item.ItemProcessor;

public class TiempoErrorCorregidoProcessor implements ItemProcessor<TiempoError, TiempoErrorCorregido> {
    @Override
    public TiempoErrorCorregido process(TiempoError tiempoError) throws Exception {
        TiempoErrorCorregido tiempoErrorCorregido = new TiempoErrorCorregido();
        tiempoErrorCorregido.setLocalidad(tiempoError.getLocalidad());
        tiempoErrorCorregido.setFecha(tiempoError.getFecha());

        // Eliminar el último carácter de la temperatura
        String temperatura = String.valueOf(tiempoError.getTemperatura());
        if (temperatura.length() > 0) {
            temperatura = temperatura.substring(0, temperatura.length() - 1);
        }
        tiempoErrorCorregido.setTemperatura(Integer.valueOf(temperatura));

        tiempoErrorCorregido.setTiempo(tiempoError.getTiempo());


        return tiempoErrorCorregido;
    }
}
