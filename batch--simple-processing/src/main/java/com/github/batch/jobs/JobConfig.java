package com.github.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobConfig {

  private final JobBuilderFactory jobBuilderFactory;

  public JobConfig(JobBuilderFactory jobBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
  }

  @Bean
  public Job printHelloWorldJob(Step printHelloStep) {
    return jobBuilderFactory.get("helloWorldJob")
        .start(printHelloStep)
        .incrementer(new RunIdIncrementer())
        .build();
  }
}
