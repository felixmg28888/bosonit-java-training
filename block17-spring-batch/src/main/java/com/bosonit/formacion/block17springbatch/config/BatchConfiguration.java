package com.bosonit.formacion.block17springbatch.config;

import com.bosonit.formacion.block17springbatch.domain.TiempoError;
import com.bosonit.formacion.block17springbatch.domain.TiempoErrorCorregido;
import com.bosonit.formacion.block17springbatch.listener.JobListener;
import com.bosonit.formacion.block17springbatch.listener.StepListener;
import com.bosonit.formacion.block17springbatch.steps.step1.TiempoNewCsvWriter;
import com.bosonit.formacion.block17springbatch.steps.step1.TiempoCsvReader;
import com.bosonit.formacion.block17springbatch.steps.step1.TiempoDBWriter;
import com.bosonit.formacion.block17springbatch.steps.step4.TiempoErrorCorregidoCsvWriter;
import com.bosonit.formacion.block17springbatch.steps.step4.TiempoErrorCorregidoDBWriter;
import com.bosonit.formacion.block17springbatch.steps.step4.TiempoErrorCorregidoProcessor;
import com.bosonit.formacion.block17springbatch.steps.step4.TiempoErrorDBReader;
import com.bosonit.formacion.block17springbatch.steps.step2.TiempoDBReader;
import com.bosonit.formacion.block17springbatch.steps.step2.TiempoRiesgoCsvWriter;
import com.bosonit.formacion.block17springbatch.steps.step2.TiempoRiesgoProcessor;
import com.bosonit.formacion.block17springbatch.steps.step2.TiempoRiesgoDBWriter;
import com.bosonit.formacion.block17springbatch.steps.step3.TiempoErrorProcessor;
import com.bosonit.formacion.block17springbatch.steps.step3.TiempoErrorCsvWriter;
import com.bosonit.formacion.block17springbatch.steps.step3.TiempoSelectecDBReader;
import com.bosonit.formacion.block17springbatch.steps.step3.TiempoErrorDBWriter;
import com.bosonit.formacion.block17springbatch.domain.Tiempo;
import com.bosonit.formacion.block17springbatch.domain.TiempoRiesgo;
import com.bosonit.formacion.block17springbatch.steps.step5.TiempoErrorCorregidoDBReader;
import com.bosonit.formacion.block17springbatch.steps.step5.TiempoWithTiempoErrorCorregidoDBWriter;
import com.bosonit.formacion.block17springbatch.steps.step5.TiempoWithTiempoErrorCorregidoProcessor;
import com.bosonit.formacion.block17springbatch.steps.step6.TiempoRiesgoWithTiempoErrorCorregidoDBWriter;
import com.bosonit.formacion.block17springbatch.steps.step6.TiempoRiesgoWithTiempoErrorCorregidoProcessor;
import com.bosonit.formacion.block17springbatch.steps.step7.TiempoWithTiempoErrorCorregidoCsvWriter;
import com.bosonit.formacion.block17springbatch.steps.step8.TiempoRiesgoWithTiempoErrorCorregidoCsvWriter;
import com.bosonit.formacion.block17springbatch.steps.step8.TiempoRiesgoWithTiempoErrorCorregidoDBReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    //-------------------------------------------------STEP 1 ---------------------------------------------------------
    @Bean
    public TiempoCsvReader tiempoCsvReader() {
        return new TiempoCsvReader();
    }

    @Bean
    public TiempoDBWriter tiempoWriter() {
        return new TiempoDBWriter();
    }

    @Bean
    public TiempoNewCsvWriter tiempoCsvWriterWriter() {
        return new TiempoNewCsvWriter();
    }

    @Bean
    public Step step1() {
        CompositeItemWriter<Tiempo> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(tiempoWriter(), tiempoCsvWriterWriter()));
        return stepBuilderFactory.get("step1")
                .<Tiempo, Tiempo>chunk(100)
                .reader(tiempoCsvReader())
                .writer(compositeWriter)
                .listener(new StepListener(1))
                .build();
    }

    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------STEP 2 ----------------------------------------------------------

    @Bean
    public TiempoDBReader tiempoDbReader() {
        return new TiempoDBReader(entityManagerFactory);
    }

    @Bean
    public TiempoRiesgoProcessor tiempoRiesgoProcessor() {
        return new TiempoRiesgoProcessor();
    }

    @Bean
    public TiempoRiesgoDBWriter tiempoRiesgoDBWriter() {
        return new TiempoRiesgoDBWriter();
    }

    @Bean
    public TiempoRiesgoCsvWriter tiempoRiesgoCsvWriter() {
        return new TiempoRiesgoCsvWriter();
    }


    @Bean
    public Step step2() {
        CompositeItemWriter<TiempoRiesgo> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(tiempoRiesgoDBWriter(), tiempoRiesgoCsvWriter()));
        return stepBuilderFactory.get("step2")
                .<Tiempo, TiempoRiesgo>chunk(100)
                .faultTolerant()
                .skipLimit(1000)
                .skip(IllegalArgumentException.class)
                .reader(tiempoDbReader())
                .processor(tiempoRiesgoProcessor())
                .writer(compositeWriter)
                .listener(new StepListener(2))

                .build();
    }

    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------STEP 3 ----------------------------------------------------------

    @Bean
    public TiempoSelectecDBReader tiempoSelectecDBReader() {
        return new TiempoSelectecDBReader(entityManagerFactory);
    }

    @Bean
    public TiempoErrorProcessor tiempoErrorProcessor() {
        return new TiempoErrorProcessor();
    }

    @Bean
    public TiempoErrorDBWriter tiempoErrorDBWriter() {
        return new TiempoErrorDBWriter();
    }

    @Bean
    public TiempoErrorCsvWriter tiempoErrorCsvWriter() {
        return new TiempoErrorCsvWriter();
    }


    @Bean
    public Step step3() {
        CompositeItemWriter<TiempoError> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(tiempoErrorDBWriter(), tiempoErrorCsvWriter()));
        return stepBuilderFactory.get("step3")
                .<Tiempo, TiempoError>chunk(100)
                .reader(tiempoSelectecDBReader())
                .processor(tiempoErrorProcessor())
                .writer(compositeWriter)
                .listener(new StepListener(3))
                .build();
    }
    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------STEP 4 ----------------------------------------------------------

    @Bean
    public TiempoErrorDBReader tiempoErrorDBReader() {
        return new TiempoErrorDBReader(entityManagerFactory);
    }

    @Bean
    public TiempoErrorCorregidoProcessor tiempoErrorCorregidoProcessor() {
        return new TiempoErrorCorregidoProcessor();
    }

    @Bean
    public TiempoErrorCorregidoCsvWriter tiempoErrorCorregidoCsvWriter() {
        return new TiempoErrorCorregidoCsvWriter();
    }

    @Bean
    public TiempoErrorCorregidoDBWriter tiempoErrorCorregidoBDWriter() {
        return new TiempoErrorCorregidoDBWriter();
    }


    @Bean
    public Step step4() {
        CompositeItemWriter<TiempoErrorCorregido> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(tiempoErrorCorregidoCsvWriter(), tiempoErrorCorregidoBDWriter()));
        return stepBuilderFactory.get("step4")
                .<TiempoError, TiempoErrorCorregido>chunk(100)
                .reader(tiempoErrorDBReader())
                .processor(tiempoErrorCorregidoProcessor())
                .writer(compositeWriter)
                .listener(new StepListener(4))
                .build();
    }

    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------STEP 5 ----------------------------------------------------------

    @Bean
    public TiempoErrorCorregidoDBReader tiempoErrorCorregidoDBReader() {
        return new TiempoErrorCorregidoDBReader(entityManagerFactory);
    }

    @Bean
    public TiempoWithTiempoErrorCorregidoProcessor tiempoWithTiempoErrorCorregidoProcessor() {
        return new TiempoWithTiempoErrorCorregidoProcessor();
    }

    @Bean
    public TiempoWithTiempoErrorCorregidoDBWriter tiempoWithTiempoErrorCorregidoDBWriter() {
        return new TiempoWithTiempoErrorCorregidoDBWriter();
    }


    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .<TiempoErrorCorregido, Tiempo>chunk(100)
                .reader(tiempoErrorCorregidoDBReader())
                .processor(tiempoWithTiempoErrorCorregidoProcessor())
                .writer(tiempoWithTiempoErrorCorregidoDBWriter())
                .listener(new StepListener(5))
                .build();
    }

    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------STEP 6 ----------------------------------------------------------

    @Bean
    public TiempoRiesgoWithTiempoErrorCorregidoProcessor tiempoRiesgoWithTiempoErrorCorregidoProcessor() {
        return new TiempoRiesgoWithTiempoErrorCorregidoProcessor();
    }

    @Bean
    public TiempoRiesgoWithTiempoErrorCorregidoDBWriter tiempoRiesgoWithTiempoErrorCorregidoDBWriter() {
        return new TiempoRiesgoWithTiempoErrorCorregidoDBWriter();
    }


    @Bean
    public Step step6() {
        return stepBuilderFactory.get("step6")
                .<TiempoErrorCorregido, TiempoRiesgo>chunk(100)
                .reader(tiempoErrorCorregidoDBReader())
                .processor(tiempoRiesgoWithTiempoErrorCorregidoProcessor())
                .writer(tiempoRiesgoWithTiempoErrorCorregidoDBWriter())
                .listener(new StepListener(6))

                .build();
    }

    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------STEP 7 ----------------------------------------------------------

    @Bean
    public TiempoWithTiempoErrorCorregidoCsvWriter tiempoWithTiempoErrorCorregidoCsvWriter() {
        return new TiempoWithTiempoErrorCorregidoCsvWriter();
    }

    @Bean
    public Step step7() {
        return stepBuilderFactory.get("step7")
                .<Tiempo, Tiempo>chunk(100)
                .reader(tiempoDbReader())
                .writer(tiempoWithTiempoErrorCorregidoCsvWriter())
                .listener(new StepListener(7))
                .build();
    }

    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------STEP 8 ----------------------------------------------------------

    @Bean
    public TiempoRiesgoWithTiempoErrorCorregidoDBReader tiempoRiesgoWithTiempoErrorCorregidoDBReader() {
        return new TiempoRiesgoWithTiempoErrorCorregidoDBReader(entityManagerFactory);
    }

    @Bean
    public TiempoRiesgoWithTiempoErrorCorregidoCsvWriter tiempoRiesgoWithTiempoErrorCorregidoCsvWriter() {
        return new TiempoRiesgoWithTiempoErrorCorregidoCsvWriter();
    }

    @Bean
    public Step step8() {
        return stepBuilderFactory.get("step8")
                .<TiempoRiesgo, TiempoRiesgo>chunk(100)
                .reader(tiempoRiesgoWithTiempoErrorCorregidoDBReader())
                .writer(tiempoRiesgoWithTiempoErrorCorregidoCsvWriter())
                .listener(new StepListener(8))
                .build();
    }

    //------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------JOB 1 ----------------------------------------------------------
    @Bean
    public JobListener jobListener() {return new JobListener();}

    @Bean
    public Job job(Step step1, Step step2, Step step3, Step step4, Step step5, Step step6, Step step7, Step step8) {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener())
                .start(step1)
                .next(step2)
                .next(step3)
                .next(step4)
                .next(step5)
                .next(step6)
                .next(step7)
                .next(step8)
                .build();
    }
    //------------------------------------------------------------------------------------------------------------------

}