package edu.codegym.toam.repository;

import edu.codegym.toam.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long> {
    Iterable<Comments>findCommentsByProperties_Id(Long propertyId);
    Iterable<Comments>findCommentsByAccount_Id(Long hostId);
    Iterable<Comments>findCommentsByProperties_IdOrderByCreateDateDesc(Long hostId);

}
