package de.cook2gether.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "post")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private String teaserUrl;
    @NonNull
    private String postTime;
    @NonNull
    private PostTypes postType;
    @NonNull
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="receipt_id", referencedColumnName = "receiptId")
    private Receipt receipt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id", referencedColumnName = "postId")
    private List<Like> likes;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id", referencedColumnName = "postId")
    private List<Comment> comments;

}
