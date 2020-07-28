package edu.codegym.toam.service.comments;

import edu.codegym.toam.model.Comments;

public interface ICommentsService {
    Iterable<Comments> findAll();

    Comments findById(Long id);

    Comments update(Comments contracts);

    void removeById(Long id);

    Comments create(Comments contracts);

    Iterable<Comments> findAllCommentByPropertyId(Long propertyId);

    Comments createCommentByPropertyId(Long propertyId,Comments comments);
}
