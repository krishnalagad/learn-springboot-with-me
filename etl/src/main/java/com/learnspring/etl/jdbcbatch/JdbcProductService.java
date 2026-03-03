package com.learnspring.etl.jdbcbatch;

import com.learnspring.etl.ProductCategory;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcProductService {

    private static final int BATCH_SIZE = 1000;

    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS jdbc_products (
                product_id BIGINT PRIMARY KEY,
                product_name VARCHAR(255),
                product_price DOUBLE,
                product_mnf_date DATE,
                product_exp_date DATE,
                product_category VARCHAR(50)
            )
            """;

    private static final String INSERT_SQL = """
            INSERT INTO jdbc_products (product_id, product_name, product_price, product_mnf_date, product_exp_date, product_category)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String uploadCsvData(MultipartFile file) {
        long startTime = System.currentTimeMillis();

        // Ensure table exists
        jdbcTemplate.execute(CREATE_TABLE_SQL);

        int totalRows = 0;

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            // Skip header line
            csvReader.readNext();

            List<JdbcProduct> batch = new ArrayList<>(BATCH_SIZE);
            String[] line;

            while ((line = csvReader.readNext()) != null) {
                JdbcProduct product = mapToProduct(line);
                batch.add(product);

                if (batch.size() == BATCH_SIZE) {
                    executeBatch(batch);
                    totalRows += batch.size();
                    batch.clear();
                }
            }

            // Insert remaining records
            if (!batch.isEmpty()) {
                executeBatch(batch);
                totalRows += batch.size();
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage(), e);
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        return String.format("JDBC Batch Insert completed! Rows inserted: %d, Time taken: %d ms", totalRows, timeTaken);
    }

    private void executeBatch(List<JdbcProduct> products) {
        jdbcTemplate.batchUpdate(INSERT_SQL, products, BATCH_SIZE, (ps, product) -> {
            ps.setLong(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setDouble(3, product.getProductPrice());
            ps.setDate(4, Date.valueOf(product.getProductMnfDate()));
            ps.setDate(5, Date.valueOf(product.getProductExpDate()));
            ps.setString(6, product.getProductCategory().name());
        });
    }

    private JdbcProduct mapToProduct(String[] line) {
        JdbcProduct product = new JdbcProduct();
        product.setProductId(Long.parseLong(line[0].trim()));
        product.setProductName(line[1].trim());
        product.setProductPrice(Double.parseDouble(line[2].trim()));
        product.setProductMnfDate(LocalDate.parse(line[3].trim()));
        product.setProductExpDate(LocalDate.parse(line[4].trim()));
        product.setProductCategory(ProductCategory.valueOf(line[5].trim()));
        return product;
    }
}
