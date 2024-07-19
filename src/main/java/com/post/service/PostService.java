package com.post.service;

import com.post.payload.PostDTO;

import java.util.List;

public interface PostService {

    public PostDTO createPost (PostDTO postDTO);
    public List<PostDTO>getListOfPost(int pageNo, int pageSize, String sortBy, String sortDir);
    public PostDTO updatePost(PostDTO postDTO,long id);
    public void deletePost(long id);
    public PostDTO findPostById(long id);
}
