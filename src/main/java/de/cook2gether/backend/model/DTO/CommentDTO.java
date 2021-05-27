package de.cook2gether.backend.model.DTO;

import de.cook2gether.backend.model.Comment;
import de.cook2gether.backend.util.Utility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int commentId;
    private String username;
    private String commentTime;
    private String message;

    public Comment toComment(){
        return Utility.convert(this, Comment.class);
    }
}
