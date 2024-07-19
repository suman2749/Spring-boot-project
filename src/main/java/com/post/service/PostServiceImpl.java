package com.post.service;

import com.post.entity.Post;
import com.post.exception.ResourceNotFound;
import com.post.payload.PostDTO;
import com.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mapToEntity(postDTO);
        Post savePost=postRepository.save(post);
        PostDTO dto=mapToDTO(savePost);
        dto.setMessage("Posted Successfully..");
        return dto;
    }
    Post mapToEntity(PostDTO postDTO){
        Post entity = new Post();
        entity.setId(postDTO.getId());
        entity.setPostTitle(postDTO.getPostTitle());
        entity.setDescription(postDTO.getDescription());
        entity.setPostType(postDTO.getPostType());
        return entity;
    }
    PostDTO mapToDTO (Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setPostTitle(post.getPostTitle());
        dto.setDescription(post.getDescription());
        dto.setPostType(post.getPostType());
        return dto;
    }

    @Override
    public List<PostDTO> getListOfPost(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC,sortBy):Sort.by(Sort.Direction.DESC,sortBy);
        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Post>all = postRepository.findAll(pageable);
        List<PostDTO> collect = all.stream().map(e -> mapToDTO(e)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Optional<Post> byId = postRepository.findById(id);
        Post post = byId.get();
        post.setId(postDTO.getId());
        post.setPostTitle(postDTO.getPostTitle());
        post.setDescription(postDTO.getPostTitle());
        post.setPostType(postDTO.getPostType());
        Post postSave =postRepository.save(post);
        PostDTO dto =mapToDTO(postSave);
        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
    
    public PostDTO findPostById(long id){
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFound("Data doest not exists"));
       PostDTO dto = mapToDTO(post);
       return dto;
    }
}
