package com.bosonit.formacion.block17springbatch.steps.step2;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import com.bosonit.formacion.block17springbatch.domain.TiempoRiesgo;
import org.springframework.batch.item.ItemProcessor;

import java.util.Calendar;

public class TiempoRiesgoProcessor implements ItemProcessor<Tiempo, TiempoRiesgo> {
    @Override
    public TiempoRiesgo process(Tiempo tiempo) {

//        if (tiempo.getTemperatura() > 50 || tiempo.getTemperatura() < -20) {
//            throw new IllegalArgumentException();
//        }else{

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tiempo.getFecha());

        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setLocalidad(tiempo.getLocalidad());
        tiempoRiesgo.setMes(calendar.get(Calendar.MONTH));
        tiempoRiesgo.setAgno(calendar.get(Calendar.YEAR));
        tiempoRiesgo.setTemperatura(tiempo.getTemperatura());
        tiempoRiesgo.setRiesgo(tiempo.getTemperatura() >= 36 ? "HIGH" :
                (tiempo.getTemperatura() < 36 && tiempo.getTemperatura() >= 32) ? "NORMAL" : "LOW");
        tiempo.setIdTiempo(tiempo.getIdTiempo());
        tiempoRiesgo.setTiempo(tiempo);
        return tiempoRiesgo;


    }}

//}

