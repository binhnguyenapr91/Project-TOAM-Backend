package edu.codegym.toam.controller;

import edu.codegym.toam.model.Comments;
import edu.codegym.toam.service.comments.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
@CrossOrigin("*")
public class CommentRestController {
    @Autowired
    ICommentsService commentsService;

    @GetMapping("")
    public ResponseEntity<Iterable<Comments>> getComment() {
        return ResponseEntity.ok(this.commentsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comments> getCommentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.commentsService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Comments> createComment(@RequestBody Comments comments) {
        try {
            return ResponseEntity.ok(this.commentsService.create(comments));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping()
    public ResponseEntity<Comments> updateComment(@RequestBody Comments comments) {
        try {
            return ResponseEntity.ok(this.commentsService.update(comments));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCommentById(@PathVariable Long id) {
        try {
            this.commentsService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<Iterable<Comments>> getPropertyComments(@PathVariable Long propertyId) {
        return ResponseEntity.ok(this.commentsService.findAllCommentByPropertyId(propertyId));
    }

    @PostMapping("create/property/{propertyId}")
    public ResponseEntity<Iterable<Comments>> createPropertyComments(@PathVariable Long propertyId) {
        return ResponseEntity.ok(this.commentsService.findAllCommentByPropertyId(propertyId));
    }
}
