package org.launchcode.roomranger.controllers;

import jakarta.validation.Valid;
import org.launchcode.roomranger.data.AssignedRoomRepository;
import org.launchcode.roomranger.data.CommentRepository;
import org.launchcode.roomranger.data.RoomRepository;
import org.launchcode.roomranger.exception.NotFoundException;
import org.launchcode.roomranger.models.AssignedRoom;
import org.launchcode.roomranger.models.Comment;
import org.launchcode.roomranger.models.Dto.CommentDto;
import org.launchcode.roomranger.models.Room;
import org.launchcode.roomranger.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
