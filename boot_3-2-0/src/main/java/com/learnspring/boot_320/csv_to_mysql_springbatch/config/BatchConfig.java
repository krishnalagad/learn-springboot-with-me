package com.learnspring.boot_320.csv_to_mysql_springbatch.config;

import com.learnspring.boot_320.csv_to_mysql_springbatch.entity.DataCsv;
import com.learnspring.boot_320.csv_to_mysql_springbatch.partition.ColumnRangePartitioner;
import com.learnspring.boot_320.csv_to_mysql_springbatch.repo.DataBatchRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing        // Not required in Springboot 3+
public class BatchConfig {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataBatchRepository dataRepository;

    @Autowired
    private DataItemWriter writer;

    @Bean
    public FlatFileItemReader<DataCsv> reader() {
        FlatFileItemReader<DataCsv> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("output.csv"));
//        reader.setResource(new FileSystemResource("/src/main/resources/output.csv"));
        reader.setName("csvReader");
        reader.setLineMapper(getLineMapper());
        reader.setLinesToSkip(1);
        return reader;
    }

    @Bean
    public DataItemProcessor processor() {
        return new DataItemProcessor();
    }

//    @Bean
//    public RepositoryItemWriter<DataCsv> writer() {
//        RepositoryItemWriter<DataCsv> writer = new RepositoryItemWriter<>();
//        writer.setRepository(this.dataRepository);
//        writer.setMethodName("save");
//        return writer;
//    }

    @Bean
    public ColumnRangePartitioner partitioner() {
        return new ColumnRangePartitioner();
    }

    @Bean
    public PartitionHandler partitionHandler(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
        taskExecutorPartitionHandler.setGridSize(4);
        taskExecutorPartitionHandler.setTaskExecutor(taskExecutor());
        taskExecutorPartitionHandler.setStep(slaveStep(jobRepository, transactionManager));

        return taskExecutorPartitionHandler;
    }

    @Bean
    public Step slaveStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("salveStep", jobRepository)
                .<DataCsv, DataCsv>chunk(250, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(this.writer)
//                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step masterStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("masterStep", jobRepository)
                .partitioner(slaveStep(jobRepository, transactionManager).getName(), partitioner())
                .partitionHandler(partitionHandler(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Job importJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("USER-IMPORT-JOB", jobRepository)
                .flow(masterStep(jobRepository, transactionManager))
                .end()
                .build();
    }

    // Alternative taskExecutor implementation
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setQueueCapacity(4);
        return taskExecutor;
    }

    //------------------------------------------------Deprecated Way ---------------------------------------------------

//    @Bean
//    public Step step1() {
//        return this.stepBuilderFactory.get("csv-step")
//                .<DataCsv, DataCsv>chunk(10)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .taskExecutor(taskExecutor())
//                .build();
//    }

//    @Bean
//    public Job importJob() {
//        return this.jobBuilderFactory.get("USER-IMPORT-JOB")
//                .flow(step1())
//                .end()
//                .build();
//    }

    // -----------------------------------------------------------------------------------------------------------------

//    @Bean
//    public TaskExecutor taskExecutor() {
//        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        asyncTaskExecutor.setConcurrencyLimit(10);
//        return asyncTaskExecutor;
//    }


    private LineMapper<DataCsv> getLineMapper() {
        DefaultLineMapper<DataCsv> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"end_year", "intensity", "sector", "topic", "insight"});
        lineTokenizer.setIncludedFields(new int[]{0, 1, 2, 3, 4});

        BeanWrapperFieldSetMapper<DataCsv> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(DataCsv.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
