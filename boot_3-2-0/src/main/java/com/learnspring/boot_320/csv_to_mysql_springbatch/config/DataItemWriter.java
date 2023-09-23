package com.learnspring.boot_320.csv_to_mysql_springbatch.config;


import com.learnspring.boot_320.csv_to_mysql_springbatch.entity.DataCsv;
import com.learnspring.boot_320.csv_to_mysql_springbatch.repo.DataBatchRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DataItemWriter implements ItemWriter<DataCsv> {

    @Autowired
    private DataBatchRepository dataRepository;

    @Override
    public void write(Chunk<? extends DataCsv> chunk) throws Exception {
//        List<DataCsv> list = new ArrayList<DataCsv>((Collection<? extends DataCsv>) chunk);
        System.out.println("Thread name: " + Thread.currentThread().getName());
        this.dataRepository.saveAll(chunk);
    }
}
