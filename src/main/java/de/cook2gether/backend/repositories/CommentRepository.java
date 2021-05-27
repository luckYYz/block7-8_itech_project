package de.cook2gether.backend.repositories;

import de.cook2gether.backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Integer> {
}
