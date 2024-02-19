package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.data.AssignedRoomRepository;
import org.launchcode.roomranger.data.CommentRepository;
import org.launchcode.roomranger.models.Comment;
import org.launchcode.roomranger.models.Dto.CommentDto;
import org.launchcode.roomranger.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/comments")
public class CommentControl {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AssignedRoomRepository assignedRoomRepository;

//    @GetMapping("/room/{id}")
//    public ResponseEntity<List<Comment>> getCommentsByRoomId(@PathVariable int id) {
//        List<Comment> comments = commentRepository.findAllByRoomId(id);
//        return ResponseEntity.ok(comments);
//    }
//    @PostMapping("/rooms/{id}/add")
//    public ResponseEntity<Comment> addCommentToRoom(@PathVariable int id, @RequestBody Comment comment) {
//
//        comment.setRoom(roomRepository.findById(id).orElse(null));
//        comment.setCreatedDate(LocalDate.now());
//        Comment savedComment = commentRepository.save(comment);
//        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
//    }

    @PostMapping()
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto){
        System.out.println(commentDto);
        Comment comment = commentService.save(commentDto);
        return ResponseEntity.ok(comment);
    }

    @GetMapping()
    public ResponseEntity<List<Comment>> getCommentsByRoom(@RequestParam int assignedRoomId) {
        List<Comment> comments = commentService.getCommentsByAssignedRoomId(assignedRoomId);
        return ResponseEntity.ok(comments);
    }


}
