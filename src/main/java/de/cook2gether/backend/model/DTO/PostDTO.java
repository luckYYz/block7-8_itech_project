package de.cook2gether.backend.model.DTO;

import de.cook2gether.backend.model.Post;
import de.cook2gether.backend.model.PostTypes;
import de.cook2gether.backend.util.Utility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {

    private int postId;
    private String description;
    private String teaserUrl;
    private String postTime;
    private PostTypes postType;
    private String username;
    private ReceiptDTO receiptDTO;
    private List<LikeDTO> likes;
    private List<CommentDTO> comments;

    public Post toPost(){
        return Utility.convert(this, Post.class);
    }
}
