package com.learnspring.boot_320.csv_to_mysql_try2_springbatch.config;

import com.learnspring.boot_320.csv_to_mysql_try2_springbatch.entity.DataCsv;
import org.springframework.batch.item.ItemProcessor;

public class DataItemProcessor implements ItemProcessor<DataCsv, DataCsv> {
    @Override
    public DataCsv process(DataCsv item) throws Exception {
        return item;
    }
}
