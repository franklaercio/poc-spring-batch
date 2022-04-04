package com.github.fixed.width.file.reader;

import com.github.fixed.width.file.domain.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FixedWidthFileStepReaderConfig {

  @StepScope
  @Bean
  public FlatFileItemReader<Customer> fixedWidthFileReader(
      @Value("#{jobParameters['filePath']}") Resource file) {
    return new FlatFileItemReaderBuilder<Customer>()
        .name("fixedWidthFileReader")
        .resource(file)
        .fixedLength()
        .columns(
            new Range[]{new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43)})
        .names("name", "lastName", "age", "email")
        .targetType(Customer.class)
        .build();
  }
}
