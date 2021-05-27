package de.cook2gether.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cook2gether.backend.model.DTO.PostDTO;
import de.cook2gether.backend.model.PostTypes;
import de.cook2gether.backend.model.User;
import de.cook2gether.backend.repositories.PostRepository;
import de.cook2gether.backend.repositories.UserRepository;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private PostDTO postDTO;

    @BeforeEach
    void setUp(){
            user = User.builder()
                    .username("uwu2")
                    .emailAddress("uwu@uwu.de")
                    .firstName("Uwu")
                    .lastName("Uwuw")
                    .password("test")
                    .build();
            postDTO = PostDTO.builder()
                .postType(PostTypes.POST)
                .description("Hallo Welt")
                .teaserUrl("t")
                .username("uwu2")
                .build();
    }

    @Test
    @SneakyThrows
    @Order(1)
    void registerUserShouldWork(){
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                //.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1d3VAdXd1LmRlIiwiZXhwIjoxNjIyMDIzNTI1LCJpYXQiOjE2MjE0MTg3MjV9.7x3upvErafRAs7H0okw3heCITpgEKhHgdxfkJQekJuc")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isEqualTo(1);
    }

    @Test
    @SneakyThrows
    @Order(2)
    void registerPresentUserNotShouldWork(){
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                //.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1d3VAdXd1LmRlIiwiZXhwIjoxNjIyMDIzNTI1LCJpYXQiOjE2MjE0MTg3MjV9.7x3upvErafRAs7H0okw3heCITpgEKhHgdxfkJQekJuc")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    @Order(3)
    void postingPostShouldWork() {

        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1d3VAdXd1LmRlIiwiZXhwIjoxNjIyNjI5MzgzLCJpYXQiOjE2MjIwMjQ1ODN9.ih89_QgJcukURS9MAt91G2c6a0yVVYneeYLh0bYIhFE")
                .content(objectMapper.writeValueAsString(postDTO)))
                .andExpect(status().isOk());

        Assertions.assertThat(postRepository.findAll().size()).isEqualTo(1);
    }
}
