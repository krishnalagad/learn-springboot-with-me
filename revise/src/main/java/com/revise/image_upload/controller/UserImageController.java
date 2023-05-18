package com.revise.image_upload.controller;

import com.revise.image_upload.entity.User;
import com.revise.image_upload.service.ImageService;
import com.revise.image_upload.service.UserImageService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/user-image")
public class UserImageController {

    @Autowired
    private UserImageService userService;

    @Autowired
    private ImageService imageService;

    @Value("${project.image}")
    private String path;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = this.userService.saveUser(user);
        return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws Exception {
        User user = this.userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Integer id) throws Exception {
        Map<String, String> resp = new HashMap<>();
        resp.put("Message", "User deleted !!");

        this.userService.deleteUser(id);
        return ResponseEntity.ok(resp);
    }

//    ------------------------------------------------------------------------------------------------------------------------
//    ------------------------------------------------Image upload API--------------------------------------------------------

    @PostMapping("/image/{id}")
    public ResponseEntity<User> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer id) throws Exception {

        User currentUser = this.userService.getUser(id);
        String fileName = null;
        File file = null;

        // Condition 1: Set image for new user and store it in directory.
        if (currentUser.getImageName() == null || currentUser.getImageName().equals("")) {

            fileName = this.imageService.uploadImage(path, image, Integer.toString(id));

            currentUser.setImageName(fileName);
            User updateUser = this.userService.updateUser(currentUser, id);
            System.out.println("Image saved !!");

            return ResponseEntity.ok(updateUser);
        }

        // Condition 2: If Image already exists then delete it form the directory and update with new image.
        if (currentUser.getImageName() != null || currentUser.getImageName() != "") {
            try {
                file = new File(path + File.separator + currentUser.getImageName());
                if (file.delete()) {
                    System.out.println("Existing Image Deleted !!");
                    fileName = this.imageService.uploadImage(path, image, Integer.toString(id));
                    System.out.println("New Image set !!");
                } else {
                    System.out.println("Image not deleted !!");
                    throw new Exception("Image not deleted");
                }
            } catch (Exception e) {
                System.out.println("Failed to deleted Image !!");
                throw new Exception("Failed to delete image !!");
            }
        }

        currentUser.setImageName(fileName);
        User updateUser = this.userService.updateUser(currentUser, id);

        return ResponseEntity.ok(updateUser);
    }

    @GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downlaodImage(@PathVariable String name, HttpServletResponse response) throws IOException {

        InputStream resource = this.imageService.getResource(path, name);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
