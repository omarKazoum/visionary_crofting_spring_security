package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner{
    @Autowired
    ApplicationContext context;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("printing beans list");
        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("end printing beans list");
    }
}
