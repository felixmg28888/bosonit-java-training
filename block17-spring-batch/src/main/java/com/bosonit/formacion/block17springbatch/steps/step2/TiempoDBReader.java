package com.bosonit.formacion.block17springbatch.steps.step2;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.database.JpaCursorItemReader;

import javax.persistence.EntityManagerFactory;

public class TiempoDBReader extends JpaCursorItemReader<Tiempo> {

    public TiempoDBReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT t FROM Tiempo t");
    }

}

