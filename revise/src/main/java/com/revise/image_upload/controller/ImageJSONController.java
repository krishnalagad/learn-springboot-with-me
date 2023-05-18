package com.revise.image_upload.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revise.image_upload.entity.User;
import com.revise.image_upload.service.ImageService;
import com.revise.image_upload.service.UserImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v2/image-json")
public class ImageJSONController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserImageService userService;

    @Value("${project.image}")
    private String path;

    private Logger logger = LoggerFactory.getLogger(ImageJSONController.class);

    @PostMapping
    public ResponseEntity<?> addUserAndImage(@RequestParam("file") MultipartFile file,
                                             @RequestParam("userData") String userData) throws Exception {
        User user = null;

        // converting string into JSON.
        try {
            user = this.mapper.readValue(userData, User.class);
        }catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request !!");
        }

        this.logger.info("User: {}", user);
        User saveUser = this.userService.saveUser(user);

        String fileName = this.imageService.uploadImage(path, file, Integer.toString(saveUser.getId()));
        saveUser.setImageName(fileName);

        User updateUser = this.userService.updateUser(saveUser, saveUser.getId());

        return ResponseEntity.ok(updateUser);
    }
}
