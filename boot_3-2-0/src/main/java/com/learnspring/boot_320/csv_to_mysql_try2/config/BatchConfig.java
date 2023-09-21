package com.learnspring.boot_320.csv_to_mysql_try2.config;

import com.learnspring.boot_320.csv_to_mysql_try2.entity.Data;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job job(JobBuilder jobBuilder, StepBuilder stepBuilder) {
        return jobBuilder.name("csvToDbJob")
                .start(stepBuilder)
                        .name("step1")
                        .<Data, Data>chunk(100)
                        .reader(new CsvItemReader<>(new ClassPathResource("data.csv")))
                        .processor(new UserItemProcessor())
                        .writer(new UserItemWriter())
                        .build())
                .build();
    }

    @Bean
    public ItemReader<String> csvItemReader() {
        return new CsvItemReader<>(new ClassPathResource("output.csv"));
    }

    @Bean
    public ItemProcessor<Data, Data> userItemProcessor() {
        return new ItemProcessor<Data, Data>() {
            @Override
            public Data process(String item) throws Exception {
                // Parse the CSV record and create a User object.
                Data user = new Data();
                user.setName(item.split(",")[0]);
                user.setEmail(item.split(",")[1]);
                return user;
            }
        };
    }

    @Bean
    public ItemWriter<Data> userItemWriter() {
        return new JpaItemWriterBuilder<Data>()
                .entityManagerFactory(entityManagerFactory())
                .build();
    }
}
