package com.bosonit.formacion.block17springbatch.steps.step8;

import com.bosonit.formacion.block17springbatch.domain.TiempoRiesgo;
import org.springframework.batch.item.database.JpaCursorItemReader;

import javax.persistence.EntityManagerFactory;

public class TiempoRiesgoWithTiempoErrorCorregidoDBReader extends JpaCursorItemReader<TiempoRiesgo> {
    public TiempoRiesgoWithTiempoErrorCorregidoDBReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT t FROM TiempoRiesgo t");
    }
}