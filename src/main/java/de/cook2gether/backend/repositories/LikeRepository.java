package de.cook2gether.backend.repositories;

import de.cook2gether.backend.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository  extends JpaRepository<Like, Integer> {
}
