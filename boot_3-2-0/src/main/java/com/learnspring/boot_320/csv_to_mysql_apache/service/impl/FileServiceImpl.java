package com.learnspring.boot_320.csv_to_mysql_apache.service.impl;

import com.learnspring.boot_320.csv_to_mysql_apache.entity.Data;
import com.learnspring.boot_320.csv_to_mysql_apache.repo.DataRepository;
import com.learnspring.boot_320.csv_to_mysql_apache.service.FileService;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private DataRepository dataRepository;

    private final static String FILE_TYPE = "text/csv";

    @Override
    public boolean hasCsvFormat(MultipartFile file) {
        if (!FILE_TYPE.equals(file.getContentType()))
            return false;
        return true;
    }

    @Override
    public void processAndSaveData(MultipartFile file) {
        try {
            List<Data> data = convertCsvToList(file.getInputStream());
            this.dataRepository.saveAll(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ByteArrayInputStream load() {
        List<Data> data = this.dataRepository.findAll();
        ByteArrayInputStream stream = convertListToCsv(data);
        return stream;
    }

    // --------------------------------------------Helper Method-----------------------------------------------------
    private List<Data> convertCsvToList(InputStream inputStream) {

        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());

            List<Data> list = new ArrayList<>();
            List<CSVRecord> records = csvParser.getRecords();

            for (CSVRecord csvRecord : records) {
                Data data = new Data(Integer.parseInt(csvRecord.get("end_year")), Integer.parseInt(csvRecord.get("intensity")),
                        csvRecord.get("sector"), csvRecord.get("topic"), csvRecord.get("insight"));
                list.add(data);
            }
            return list;

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ByteArrayInputStream convertListToCsv(List<Data> allData) {
        CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);

            // write one row with header names before writing actual data.
            List<String> headers = Arrays.asList("end_year", "intensity", "sector", "topic", "insight");
            csvPrinter.printRecord(headers);

            for (Data data : allData) {
                List<String> list = Arrays.asList(String.valueOf(data.getEnd_year()), String.valueOf(data.getIntensity()),
                        data.getSector(), data.getTopic(), data.getInsight());
                csvPrinter.printRecord(list);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
