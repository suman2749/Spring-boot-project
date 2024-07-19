package com.post.payload;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {

    private long id;
    @Size(max = 20,min = 2,message = "Post Title suppose to be more than 2 character")
    private String postTitle;
    @Size(min=2,max = 100,message = "Description suppose to be more than 2 character and maximum 100")
    private String description;
    private String postType;
    private String message;
}
