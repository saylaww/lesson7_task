package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.LavozimDto;
import uz.pdp.lesson7.payload.PostDto;
import uz.pdp.lesson7.repository.LavozimRepository;
import uz.pdp.lesson7.repository.PostRepository;
import uz.pdp.lesson7.service.LavozimService;
import uz.pdp.lesson7.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;


    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addPost(@Valid @RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id) {
        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_POST')")
    @PutMapping
    public HttpEntity<?> editPost(@PathVariable Long id,
            @Valid @RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAllPost() {
        ApiResponse apiResponse = postService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
