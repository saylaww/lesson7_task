package uz.pdp.lesson7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.CommentDto;
import uz.pdp.lesson7.payload.PostDto;
import uz.pdp.lesson7.repository.PostRepository;
import uz.pdp.lesson7.service.CommentService;
import uz.pdp.lesson7.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    PostRepository postRepository;


    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping("/addComment")
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_COMMENT')")
    @PutMapping("/editComment/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id,
            @Valid @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.editComment(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAllComent() {
        ApiResponse apiResponse = commentService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deleteMyComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
