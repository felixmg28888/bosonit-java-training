package com.bosonit.formacion.block17springbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

@Slf4j
public class StepListener extends StepExecutionListenerSupport {

    private int stepNumber;

    public StepListener(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Iniciando step{}", stepNumber);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Finalizando step{}", stepNumber);
        return null;
    }
}