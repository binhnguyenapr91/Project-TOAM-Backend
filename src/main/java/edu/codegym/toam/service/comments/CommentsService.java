package edu.codegym.toam.service.comments;

import edu.codegym.toam.model.Comments;
import edu.codegym.toam.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService implements ICommentsService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Iterable<Comments> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comments findById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comments update(Comments contracts) {
        return commentRepository.save(contracts);
    }

    @Override
    public void removeById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comments create(Comments contracts) {
        return commentRepository.save(contracts);
    }

    @Override
    public Iterable<Comments> findAllCommentByPropertyId(Long propertyId) {
        return commentRepository.findCommentsByProperties_Id(propertyId);
    }

}
