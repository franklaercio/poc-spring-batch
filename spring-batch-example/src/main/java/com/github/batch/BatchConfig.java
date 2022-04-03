package com.github.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;

  private final StepBuilderFactory stepBuilderFactory;

  public BatchConfig(
      JobBuilderFactory jobBuilderFactory,
      StepBuilderFactory stepBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Bean
  public Job printHelloWorldJob() {
    return jobBuilderFactory.get("helloWorldJob").start(printHelloWorldStep()).incrementer(new RunIdIncrementer()).build();
  }

  public Step printHelloWorldStep() {
    return stepBuilderFactory.get("helloWorldStep").tasklet(printHelloWorldTasklet(null)).build();
  }

  @Bean
  @StepScope
  public Tasklet printHelloWorldTasklet(@Value("#{jobParameters['nome']}") String name) {
    return (stepContribution, chunkContext) -> {
      System.out.println("Hello World, " + name + "!");
      return RepeatStatus.FINISHED;
    };
  }
}
