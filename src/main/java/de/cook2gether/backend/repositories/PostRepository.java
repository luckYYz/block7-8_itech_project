package de.cook2gether.backend.repositories;

import de.cook2gether.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByUsername(String username);
    @Query(nativeQuery = true, value = "select * from post order by post_id DESC")
    List<Post> findAll();
    @Query(nativeQuery = true, value = "select * from post where post_time = ?1")
    Optional<Post> getPostByTime(String time);
}
