package com.github.fixed.width.file.writer;

import com.github.fixed.width.file.domain.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixedWidthFileWriterConfig {
	@Bean
	public ItemWriter<Customer> fixedWidthFileWriter() {
		return items -> items.forEach(System.out::println);
	}
}
