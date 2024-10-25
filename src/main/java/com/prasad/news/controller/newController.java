package com.prasad.news.controller;

import com.prasad.news.model.News;
import com.prasad.news.model.NewsType;
import com.prasad.news.model.Posts;
import com.prasad.news.model.User;
import com.prasad.news.service.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
public class newController {

    @Autowired
    private NewsTypeService newsType;

    @Autowired
    private UserService userService;

    @Autowired
    private S3Service s3Service;
    @Autowired
    private NewsService newsService;

    @Autowired
    private PostsService postsService;

    @GetMapping("/news")
    public String getAllNews(){
        return "Hello";
    }

    @PostMapping("/newstype")
    public String upsertNewsType(@RequestBody NewsType news){
        newsType.upsertNewsType(news);
        return "Success";
    }

    @PostMapping("/user")
    public ResponseEntity<String> upsertUser(@RequestBody User user){
        try {
            userService.upsertUser(user);
            return ResponseEntity.ok("Success");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/news")
    public ResponseEntity<String> upsertNews(@RequestBody News news){
        newsService.insertNews(news);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/uploads3/{bucketName}")
    @SneakyThrows(IOException.class)
    public ResponseEntity<?> uploadFile(
            @PathVariable("bucketName") String bucketName,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String contentType = file.getContentType();
        long fileSize = file.getSize();
        InputStream inputStream = file.getInputStream();
        String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileWithoutExtension=originalFileName.substring(0,originalFileName.lastIndexOf("."));
        String UUID= java.util.UUID.randomUUID().toString();
        String fileName=fileWithoutExtension+UUID+extension;

        s3Service.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);

        return ResponseEntity.ok().body("File uploaded successfully "+ fileName);

    }

    @PostMapping("posts")
    public ResponseEntity<?> uploadNews(@RequestBody Posts posts){

        System.out.println(posts);
        postsService.upsertNews(posts);
    return ResponseEntity.ok().body("Success");
    }
}
