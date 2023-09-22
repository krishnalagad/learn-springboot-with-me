package com.learnspring.boot_320.csv_to_mysql_apache.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface FileService {
    boolean hasCsvFormat(MultipartFile file);

    public void processAndSaveData(MultipartFile file);

    ByteArrayInputStream load();
}
