package com.post.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.swing.text.AbstractDocument;
@Data
@Document(collection="post")
public class Post {
    private long id;
    private String postTitle;
    private String description;
    private String postType;
}
