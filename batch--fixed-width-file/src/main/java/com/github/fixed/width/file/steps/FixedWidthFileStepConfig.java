package com.github.fixed.width.file.steps;

import com.github.fixed.width.file.domain.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixedWidthFileStepConfig {
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step fixedWidthFileStep(ItemReader<Customer> fixedWidthFileReader,
			ItemWriter<Customer> fixedWidthFileWriter) {
		return stepBuilderFactory
				.get("fixedWidthFileStep")
				.<Customer, Customer>chunk(1)
				.reader(fixedWidthFileReader)
				.writer(fixedWidthFileWriter)
				.build();
	}
}
