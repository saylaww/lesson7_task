package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Comment;
import uz.pdp.lesson7.entity.Post;
import uz.pdp.lesson7.entity.User;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.CommentDto;
import uz.pdp.lesson7.repository.CommentRepository;
import uz.pdp.lesson7.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;


    public ApiResponse addComment(CommentDto commentDto) {
        Optional<Post> byId = postRepository.findById(commentDto.getPostId());
        if (!byId.isPresent()){
            return new ApiResponse("Bunday id li post topilmadi", false);
        }
        Post post = byId.get();
        Comment comment = new Comment(commentDto.getText(),post);
        commentRepository.save(comment);

        return new ApiResponse("Comment qo'shildi", true);
    }

    public ApiResponse editComment(Long id, CommentDto commentDto) {
        Optional<Comment> byId = commentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Bunday id li comment topilmadi", false);
        }
        Comment comment = byId.get();
        comment.setText(commentDto.getText());

        commentRepository.save(comment);

        return new ApiResponse("Comment edit qilindi", true);
    }

    public ApiResponse deletePost(Long id) {
        Optional<Comment> byId = commentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Bunday id li comment topilmadi", false);
        }
        commentRepository.deleteById(id);
        return new ApiResponse("Comment o'chirildi", true);
    }

    public ApiResponse getAll() {
        List<Comment> all = commentRepository.findAll();
        return new ApiResponse("All Comment", true, all);
    }


    public ApiResponse deleteMyComment(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Long createdBy = user.getId();

        boolean b = commentRepository.existsByIdAndCreatedBy(id, createdBy);
        if (!b){
            return new ApiResponse("Error", false);
        }
        commentRepository.deleteById(id);


        return new ApiResponse("Comment o'chirildi", true);
    }
}
