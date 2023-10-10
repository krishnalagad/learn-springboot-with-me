package com.learnspring.boot_320.csv_to_mysql_springbatch.controller;

import com.learnspring.boot_320.csv_to_mysql_springbatch.service.FileService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
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

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private FileService fileService;

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

        String fileName = this.fileService.saveFileToDirectory(file);

//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
//        try {
//            this.jobLauncher.run(this.job, jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
//                 JobInstanceAlreadyCompleteException | JobRestartException e) {
//            throw new RuntimeException(e);
//        }
        return ResponseEntity.ok(fileName);
    }
}