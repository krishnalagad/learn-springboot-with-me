package com.learnspring.boot_320.csv_to_mysql_springbatch.controller;

import com.learnspring.boot_320.csv_to_mysql_springbatch.service.FileService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private FileService fileService;

    private final String PATH = "D:\\Installed Software\\Github Desktop\\learn-springboot-with-me\\boot_3-2-0\\src\\main\\resources\\";

    @PostMapping("/import")
    public ResponseEntity<?> csvToDb() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        try {
            this.jobLauncher.run(this.job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobRestartException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("Done");
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadAndSaveFile(@RequestParam("file") MultipartFile file) throws Exception {

//        String fileName = this.fileService.saveFileToDirectory(file);
        String fileName = file.getOriginalFilename();
        File fileToImport = new File(this.PATH + fileName);
        Files.deleteIfExists(Paths.get(this.PATH + fileName));

        file.transferTo(fileToImport);

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            JobExecution execution = this.jobLauncher.run(this.job, jobParameters);
            if (execution.getExitStatus().getExitCode().equals(ExitStatus.COMPLETED)) {
                // delete file after completing operation.
                Files.deleteIfExists(Paths.get(this.PATH + fileName));
            }
        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobRestartException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(fileName);
    }
}