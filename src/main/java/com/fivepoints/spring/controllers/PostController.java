package com.fivepoints.spring.controllers;

import com.fivepoints.spring.entities.Post;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> saveNewPost(@RequestBody Post post)
    {
        Post savedPost =  this.postService.saveNewPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts()
    {
        List<Post> listPosts = this.postService.getAllPost();
        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPostByID(@PathVariable("id") long id)
    {
        Post post = this.postService.findPostByID(id);
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Post not found!"), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUserByID(@PathVariable("id") long id, @RequestBody Post post)
    {
        String message = this.postService.updatePostByID(id, post);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") long id)
    {
        String message = this.postService.deletePostById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
}
