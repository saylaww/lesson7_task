package uz.pdp.lesson7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson7.entity.Post;
import uz.pdp.lesson7.payload.ApiResponse;
import uz.pdp.lesson7.payload.PostDto;
import uz.pdp.lesson7.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(PostDto postDto) {

        boolean exists = postRepository.existsByTitle(postDto.getTitle());
        if (exists){
            return new ApiResponse("Bunday title li post mavjud", false);
        }
        Post post = new Post(postDto.getTitle(),postDto.getText(),postDto.getUrl());

        postRepository.save(post);

        return new ApiResponse("Post saqlandi", true);
    }

    public ApiResponse editPost(Long id, PostDto postDto) {
        Optional<Post> byId = postRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Bunday id li post mavjud emas", false);
        }
        Post post = byId.get();
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setUrl(postDto.getUrl());

        postRepository.save(post);
        return new ApiResponse("Post edit bo'ldi!!!", true);
    }

    public ApiResponse deletePost(Long id) {
        boolean existsById = postRepository.existsById(id);
        if (!existsById)
            return new ApiResponse("Bunday id li post bazada topilmadi", false);

        postRepository.deleteById(id);
        return new ApiResponse("Post o'chirildi", true);
    }

    public ApiResponse getAll() {
        List<Post> all = postRepository.findAll();
        return new ApiResponse("All Posts", true, all);
    }
}
