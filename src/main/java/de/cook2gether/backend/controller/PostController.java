package de.cook2gether.backend.controller;

import de.cook2gether.backend.model.DTO.CommentDTO;
import de.cook2gether.backend.model.DTO.PostDTO;
import de.cook2gether.backend.services.PostService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> createReceipt(@RequestBody PostDTO postDTO){
        postService.savePost(postService.createPost(postDTO));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/post/like")
    public ResponseEntity<?> likePost(@RequestParam int postId, @RequestParam String username){
        postService.addLike(username, postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/post/like")
    public ResponseEntity<?> deleteLike(@RequestParam int postId, @RequestParam String username){
        postService.deleteLike(username, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/post/comment")
    public ResponseEntity<?> commentPost(@RequestParam int postId, @RequestBody CommentDTO commentDTO){
        postService.addComment(postId, commentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/post/comment")
    public ResponseEntity<?> deleteComment(@RequestParam int postId, @RequestParam int commentId){
        postService.deleteComment(commentId, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(){
        return ResponseEntity.ok(postService.getPostList());
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPost(@RequestParam int postId) throws NotFoundException {
       return ResponseEntity.ok(postService.getPostById(postId));
    }
}
