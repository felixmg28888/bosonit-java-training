package com.bosonit.formacion.block17springbatch.steps.step3;

import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import org.springframework.batch.item.database.JpaCursorItemReader;

import javax.persistence.EntityManagerFactory;

public class TiempoSelectecDBReader extends JpaCursorItemReader<Tiempo> {

    public TiempoSelectecDBReader(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
        setQueryString("SELECT t FROM Tiempo t WHERE t.temperatura > 50 OR t.temperatura < -20");
    }
}