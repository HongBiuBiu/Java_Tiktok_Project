package com.tictok.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentBO {

    @NotBlank(message = "Incomplete message")
    private String vlogerId;

    @NotBlank(message = "Incomplete message")
    private String fatherCommentId;

    @NotBlank(message = "Incomplete message")
    private String vlogId;

    @NotBlank(message = "Current user information is incorrect, please try to log in again")
    private String commentUserId;

    @NotBlank(message = "Comment content cannot be empty")
    @Length(max = 50, message = "The length of the comment content cannot exceed 50")
    private String content;
}