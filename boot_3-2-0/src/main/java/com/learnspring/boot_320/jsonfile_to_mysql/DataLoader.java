package com.learnspring.boot_320.jsonfile_to_mysql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnspring.boot_320.jsonfile_to_mysql.entity.Post;
import com.learnspring.boot_320.jsonfile_to_mysql.repo.PostRepository;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final PostRepository postRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(PostRepository postRepository, ObjectMapper objectMapper) {
        this.postRepository = postRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = new ArrayList<>();
        JsonNode json;

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/blog-posts.json")) {
            json = this.objectMapper.readValue(inputStream, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }

        JsonNode edges = this.getEdges(json);
        for (JsonNode edge : edges) {
            posts.add(this.createPostFromNode(edge));
        }

        this.postRepository.saveAll(posts);
    }

    private Post createPostFromNode(JsonNode edge) {
        JsonNode node = edge.get("node");
        String id = node.get("id").asText();
        String title = node.get("title").asText();
        String slug = node.get("slug").asText();
        String date = node.get("date").asText();
        int timeToRead = node.get("timeToRead").asInt();
        String tags = this.extractTags(node);

        return new Post(id, title, slug, LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy")), timeToRead, tags);
    }

    private String extractTags(JsonNode node) {
        JsonNode tags = node.get("tags");
        StringBuilder sb = new StringBuilder();
        for (JsonNode tag : tags) {
            sb.append(tag.get("title").asText());
            sb.append(",");
        }
        return sb.isEmpty() ? sb.toString() : sb.substring(0, sb.length() - 1);

    }

    private JsonNode getEdges(JsonNode json) {
        return Optional.ofNullable(json)
                .map(j -> j.get("data"))
                .map(j -> j.get("allPost"))
                .map(j -> j.get("edges"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid JSON Object"));
    }
}
