package com.learnspring.boot_320.csv_to_mysql_apache.controller;

import com.learnspring.boot_320.csv_to_mysql_apache.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/csv")
public class FileController {

    @Autowired
    private FileService fileService;
}
