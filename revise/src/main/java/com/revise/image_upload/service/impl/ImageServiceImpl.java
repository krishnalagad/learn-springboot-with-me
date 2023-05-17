package com.revise.image_upload.service.impl;

import com.revise.image_upload.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public String uploadImage(String path, MultipartFile file, String userId) throws IOException {
        // get file name
        String name = file.getOriginalFilename();

        // random name generation of image while uploading to store in folder.
        String randomId = UUID.randomUUID().toString();
        String fileName1 = "USER$" + userId + "-" + randomId.concat(name.substring(name.lastIndexOf(".")));

        // full path of file
        String filePath = path + File.separator + fileName1;

        // create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        // file copy in folder
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
