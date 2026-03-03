package com.learnspring.etl.loaddatainfile;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class LoadDataProductService {

    private final DataSource dataSource;

    public LoadDataProductService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String uploadCsvData(MultipartFile file) {
        long startTime = System.currentTimeMillis();

        // Save uploaded file to a temp location
        Path tempFile;
        try {
            tempFile = Files.createTempFile("loaddata_", ".csv");
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error saving uploaded file: " + e.getMessage(), e);
        }

        int rowsAffected;
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {

            // Convert Windows path to forward slashes for MySQL
            String filePath = tempFile.toAbsolutePath().toString().replace("\\", "/");

            String loadDataSql = String.format("""
                    LOAD DATA LOCAL INFILE '%s'
                    INTO TABLE loaddata_products
                    FIELDS TERMINATED BY ','
                    ENCLOSED BY '"'
                    LINES TERMINATED BY '\\n'
                    IGNORE 1 LINES
                    (product_id, product_name, product_price, product_mnf_date, product_exp_date, product_category)
                    """, filePath);

            rowsAffected = statement.executeUpdate(loadDataSql);

        } catch (SQLException e) {
            throw new RuntimeException("Error executing LOAD DATA INFILE: " + e.getMessage(), e);
        } finally {
            // Clean up temp file
            try {
                Files.deleteIfExists(tempFile);
            } catch (IOException ignored) {
            }
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        return String.format("LOAD DATA INFILE completed! Rows inserted: %d, Time taken: %d ms", rowsAffected,
                timeTaken);
    }
}
