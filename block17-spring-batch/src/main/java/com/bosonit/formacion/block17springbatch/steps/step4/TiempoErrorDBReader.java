package com.bosonit.formacion.block17springbatch.steps.step4;

import com.bosonit.formacion.block17springbatch.domain.TiempoError;
import org.springframework.batch.item.database.JpaCursorItemReader;

import javax.persistence.EntityManagerFactory;

public class TiempoErrorDBReader extends JpaCursorItemReader<TiempoError> {
    public TiempoErrorDBReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT t FROM TiempoError t");
    }
}
