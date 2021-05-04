package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndCreatedBy(Long id, Long createdBy);
    boolean existsByIdAndCreatedBy(Long id, Long createdBy);



}
