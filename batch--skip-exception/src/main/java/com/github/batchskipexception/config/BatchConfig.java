package com.github.batchskipexception.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Bean
  public Job skipExceptions(Step fixedWidthFileStep) {
    return this.jobBuilderFactory.get("skipExceptions")
        .start(fixedWidthFileStep)
        .build();
  }
}
