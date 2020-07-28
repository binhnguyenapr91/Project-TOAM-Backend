package edu.codegym.toam.repository;

import edu.codegym.toam.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long> {
    Iterable<Comments>findCommentsByProperties_Id(Long propertyId);
}
