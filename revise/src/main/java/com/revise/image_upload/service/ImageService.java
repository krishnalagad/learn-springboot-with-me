package com.revise.image_upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    String uploadImage(String path, MultipartFile file, String userId) throws IOException;

    InputStream getResource(String path, String fileName) throws FileNotFoundException;

    String readPDF(InputStream inputStream) throws IOException;
}
