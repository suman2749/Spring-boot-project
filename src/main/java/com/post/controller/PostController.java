package com.post.controller;

import com.post.payload.PostDTO;
import com.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mongo/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?>createPost(@Valid @RequestBody PostDTO postDTO, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        PostDTO postDto=postService.createPost(postDTO);
        return new ResponseEntity<>(postDto,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PostDTO>>listOfPost(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "5",required = false)int pageSize,
            @RequestParam(name="sortBy",defaultValue = "0",required = false)String sortBy,
            @RequestParam(name="sortDir",defaultValue = "0",required = false)String sortDir
    ){
        List<PostDTO> listOfPost = postService.getListOfPost( pageNo,pageSize,sortBy,sortDir );
        return new ResponseEntity<>(listOfPost,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<PostDTO>updatePost(@RequestBody PostDTO postDTO,@RequestParam long id){
        PostDTO postDTO1 = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postDTO1,HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String>deletePost(long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Data is deleted", HttpStatus.OK);
    }
    @GetMapping("/find/ById")
    public ResponseEntity<PostDTO> findPost(@RequestParam long id){
        PostDTO postById = postService.findPostById(id);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }
}
