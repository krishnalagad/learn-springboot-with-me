package com.learnspring.etl.jpabatch;

import com.learnspring.etl.ProductCategory;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

@Service
public class JpaProductService {

    private static final int BATCH_SIZE = 1000;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String uploadCsvData(MultipartFile file) {
        long startTime = System.currentTimeMillis();
        int totalRows = 0;

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            // Skip header line
            csvReader.readNext();

            String[] line;

            while ((line = csvReader.readNext()) != null) {
                JpaProduct product = mapToProduct(line);
                entityManager.persist(product);
                totalRows++;

                if (totalRows % BATCH_SIZE == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }

            // Flush remaining records
            entityManager.flush();
            entityManager.clear();

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage(), e);
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        return String.format("JPA Batch Insert completed! Rows inserted: %d, Time taken: %d ms", totalRows, timeTaken);
    }

    private JpaProduct mapToProduct(String[] line) {
        JpaProduct product = new JpaProduct();
        product.setProductId(Long.parseLong(line[0].trim()));
        product.setProductName(line[1].trim());
        product.setProductPrice(Double.parseDouble(line[2].trim()));
        product.setProductMnfDate(LocalDate.parse(line[3].trim()));
        product.setProductExpDate(LocalDate.parse(line[4].trim()));
        product.setProductCategory(ProductCategory.valueOf(line[5].trim()));
        return product;
    }
}
