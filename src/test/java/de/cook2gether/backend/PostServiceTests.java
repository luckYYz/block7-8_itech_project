package de.cook2gether.backend;

import de.cook2gether.backend.model.DTO.PostDTO;
import de.cook2gether.backend.model.Post;
import de.cook2gether.backend.model.PostTypes;
import de.cook2gether.backend.repositories.CommentRepository;
import de.cook2gether.backend.repositories.LikeRepository;
import de.cook2gether.backend.repositories.PostRepository;
import de.cook2gether.backend.services.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.MappingException;

public class PostServiceTests {

    @Mock private PostRepository postRepository;
    @Mock private LikeRepository likeRepository;
    @Mock private CommentRepository commentRepository;
    private PostService postService;
    private PostDTO postDTO;

    @BeforeEach
    public void setUp(){
        postService = new PostService(postRepository, commentRepository, likeRepository);
        postDTO = PostDTO.builder()
                .postType(PostTypes.POST)
                .description("Test")
                .teaserUrl("test")
                .username("a")
                .build();
    }

    @Test
    void currentPostTimeShouldBeSet(){
        //given
        Post newPost = postService.createPost(postDTO);
        //when
        //then
        Assertions.assertThat(newPost.getPostTime()).isNotEmpty();
    }

    @Test
    void usernameNullShouldThrowException(){
        //given
        //when
        postDTO.setUsername(null);
        //then
        org.junit.jupiter.api.Assertions.assertThrows(MappingException.class, postDTO::toPost);
    }

}
