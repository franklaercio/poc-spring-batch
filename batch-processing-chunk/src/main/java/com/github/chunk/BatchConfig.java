package com.github.chunk;

import java.util.Arrays;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

  private final JobBuilderFactory jobBuilderFactory;

  private final StepBuilderFactory stepBuilderFactory;

  public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Bean
  public Job printEvenOrOdd() {
    return jobBuilderFactory.get("printEvenOrOddJob").start(printEvenOrOddStep())
        .incrementer(new RunIdIncrementer()).build();
  }

  public Step printEvenOrOddStep() {
    return stepBuilderFactory.get("printEvenOrOddStep")
        .<Integer, String>chunk(1)
        .reader(countTenNumbers())
        .processor(evenOrOddProcessor())
        .writer(printWriter())
        .build();
  }

  public IteratorItemReader<Integer> countTenNumbers() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    return new IteratorItemReader<>(numbers.iterator());
  }

  public FunctionItemProcessor<Integer, String> evenOrOddProcessor() {
    return new FunctionItemProcessor<>(
        item -> item % 2 == 0 ? String.format("Item %s is Even", item)
            : String.format("Item %s is Odd", item));
  }

  public ItemWriter<String> printWriter() {
    return items -> items.forEach(System.out::println);
  }

}
