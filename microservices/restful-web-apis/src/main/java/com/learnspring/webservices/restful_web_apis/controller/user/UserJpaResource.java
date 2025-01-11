package com.learnspring.webservices.restful_web_apis.controller.user;

import com.learnspring.webservices.restful_web_apis.entity.post.Post;
import com.learnspring.webservices.restful_web_apis.entity.user.User;
import com.learnspring.webservices.restful_web_apis.exceptions.UserNotFoundException;
import com.learnspring.webservices.restful_web_apis.repository.PostRepository;
import com.learnspring.webservices.restful_web_apis.repository.UserRepository;
import com.learnspring.webservices.restful_web_apis.service.user.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user/jpa")
public class UserJpaResource {
    private final UserDaoService userDaoService;

    private final UserRepository userRepository;

    private final MessageSource messageSource;

    private final PostRepository postRepository;

    public UserJpaResource(UserDaoService userDaoService, UserRepository userRepository, MessageSource messageSource, PostRepository postRepository) {
        this.userDaoService = userDaoService;
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getOneUser(@PathVariable Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Id:" + id);

        EntityModel<User> userEntityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(link.withRel("all-users"));

        return userEntityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        this.userDaoService.deleteById(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getPostsOfUser(@PathVariable Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Id: " + id);

        return user.get().getPosts();
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        User savedUser = this.userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body("User created successfully.");
    }

    @GetMapping("/users/i18n-api")
    public String helloWorldI18N() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("welcome.message", null, "Default message", locale);

    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Id: " + id);

        post.setUser(user.get());
        Post savedPost = this.postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}