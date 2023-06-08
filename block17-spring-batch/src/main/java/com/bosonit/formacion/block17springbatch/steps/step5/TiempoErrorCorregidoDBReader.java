package com.bosonit.formacion.block17springbatch.steps.step5;

import com.bosonit.formacion.block17springbatch.domain.TiempoErrorCorregido;
import org.springframework.batch.item.database.JpaCursorItemReader;

import javax.persistence.EntityManagerFactory;

public class TiempoErrorCorregidoDBReader extends JpaCursorItemReader<TiempoErrorCorregido> {
    public TiempoErrorCorregidoDBReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT t FROM TiempoErrorCorregido t");
    }
}
