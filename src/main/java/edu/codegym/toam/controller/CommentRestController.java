package edu.codegym.toam.controller;

import edu.codegym.toam.model.Comments;
import edu.codegym.toam.model.Contracts;
import edu.codegym.toam.service.comments.ICommentsService;
import edu.codegym.toam.service.contract.IContractService;
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

    @Autowired
    IContractService contractService;

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

    //    Lấy tất cả comments của 1 property
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<Iterable<Comments>> getPropertyComments(@PathVariable Long propertyId) {
        return ResponseEntity.ok(this.commentsService.findAllCommentByPropertyId(propertyId));
    }

    // Tạo comments của 1 property (chỉ cho phép những account có trong hợp đồng vs nhà này dc phép bình luận)
    @PostMapping("create/property")
    public ResponseEntity<Comments> createPropertyComments(@RequestBody Comments comments) {
        try {
            Long renterId = comments.getAccount().getId();
            Long propertyId = comments.getProperties().getId();
            Iterable<Contracts> checkContracts = contractService.findAllContractsByRenterIdAndPropertyId(renterId, propertyId);
            if (!checkContracts.iterator().hasNext()) throw new Exception();

            return ResponseEntity.ok(this.commentsService.createCommentByPropertyId(propertyId, comments));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
