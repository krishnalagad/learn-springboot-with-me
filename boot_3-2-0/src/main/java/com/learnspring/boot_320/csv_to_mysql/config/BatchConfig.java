package com.learnspring.boot_320.csv_to_mysql.config;

import com.learnspring.boot_320.csv_to_mysql.entity.Data;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // --------------------------------Deprecation Alternatives Not used yet  -------------------------------------
    @Autowired
    private JobBuilder jobBuilder;

    @Autowired
    private StepBuilder stepBuilder;
    // -----------------------------------------------------------------------------------------------

    // Item Reader
    @Bean
    public FlatFileItemReader<Data> reader() {
        FlatFileItemReader<Data> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("output.csv"));
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    @Bean
    public DataItemProcessor dataItemProcessor() {
        return new DataItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Data> writer() {
        JdbcBatchItemWriter<Data> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Data>());
        writer.setSql("insert into csv_to_mysql_data(id,end_year,intensity,sector,insight) values(:id,:end_year,:intensity,:sector,:insight)");
        writer.setDataSource(this.dataSource);
        return writer;
    }

    @Bean
    public Job importJob() {
        return this.jobBuilderFactory.get("USER-IMPORT-JOB")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1")
                .<Data, Data>chunk(10)
                .reader(reader())
                .processor(dataItemProcessor())
                .writer(writer())
                .build();
    }


    // ---------------------------------------------Helper methods------------------------------------------------
    private LineMapper<Data> getLineMapper() {
        DefaultLineMapper<Data> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"end_year", "intensity", "sector", "insight"});
        lineTokenizer.setIncludedFields(new int[]{0, 3, 4, 6});

        BeanWrapperFieldSetMapper<Data> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Data.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
