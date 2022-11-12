package com.github.batchskipexception.writers;

import com.github.batchskipexception.domain.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixedWriter {
  @Bean
  public ItemWriter<Customer> fixedWidthFileWriter() {
    return items -> {
      for (Customer customer: items) {
        if(customer.getName().equals("Maria")) {
          throw new Exception();
        }
        System.out.println(customer);
      }
    };
  }
}
