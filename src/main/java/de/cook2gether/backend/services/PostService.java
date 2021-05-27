package de.cook2gether.backend.services;

import de.cook2gether.backend.model.Comment;
import de.cook2gether.backend.model.DTO.CommentDTO;
import de.cook2gether.backend.model.DTO.PostDTO;
import de.cook2gether.backend.model.Like;
import de.cook2gether.backend.model.Post;
import de.cook2gether.backend.repositories.CommentRepository;
import de.cook2gether.backend.repositories.LikeRepository;
import de.cook2gether.backend.repositories.PostRepository;
import de.cook2gether.backend.util.Utility;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public Post createPost(PostDTO postDTO){
        postDTO.setPostTime(LocalDateTime.now().toString());
        return postDTO.toPost();
    }

    public void savePost(Post post){
        postRepository.save(post);
    }

    public List<PostDTO> getPostList(){
        List<Post> posts = postRepository.findAll();
        return posts
                .stream()
                .map(post -> Utility.convert(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    public void deletePost(PostDTO post){
        postRepository.delete(post.toPost());
    }

    public void addComment(int postId, CommentDTO commentDTO) {
        Optional<Post> post = postRepository.findById(postId);
        post.ifPresent(cPost -> {
            commentDTO.setCommentTime(LocalDateTime.now().toString());
            cPost.getComments().add(commentDTO.toComment());
            commentRepository.save(commentDTO.toComment());
            savePost(cPost);
        });
    }

    public void addLike(String username, int postId) {
        Optional<Post> post = postRepository.findById(postId);
        log.info("add like called");
        post.ifPresent(cPost -> {
            Like like = Like.builder()
                    .likeTime(LocalDateTime.now().toString())
                    .username(username)
                    .build();
            likeRepository.save(like);
            cPost.getLikes().add(like);
            savePost(cPost);
        });
    }

    public void deleteLike(String username, int postId){
        Optional<Post> post = postRepository.findById(postId);
        post.ifPresent(cPost -> {
            List<Like> removeLike = post.get().getLikes()
                    .stream()
                    .filter(like -> like.getUsername().equals(username))
                    .collect(Collectors.toList());
            post.get().getLikes().remove(removeLike.get(0));
            postRepository.save(post.get());
        });
    }

    public void deleteComment(int commentId, int postId){
        Optional<Post> post = postRepository.findById(postId);
        post.ifPresent(cPost -> {
            List<Comment> comments = post.get().getComments()
                    .stream()
                    .filter(comment -> comment.getCommentId() == commentId)
                    .collect(Collectors.toList());
            commentRepository.delete(comments.get(0));
            post.get().getComments().remove(comments.get(0));
            postRepository.save(post.get());
        });
    }

    public Post getPostById(int postId) throws NotFoundException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isEmpty()) throw new NotFoundException("Post not found!");
        else return optionalPost.get();
    }
}
