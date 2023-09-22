package com.learnspring.boot_320.csv_to_mysql_apache.service.impl;

import com.learnspring.boot_320.csv_to_mysql_apache.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private final static String FILE_TYPE = "text/csv";

    @Override
    public boolean hasCsvFormat(MultipartFile file) {
        if (!FILE_TYPE.equals(file.getContentType()))
            return false;
        return true;
    }

    @Override
    public void processAndSaveData(MultipartFile file) {

    }
}
