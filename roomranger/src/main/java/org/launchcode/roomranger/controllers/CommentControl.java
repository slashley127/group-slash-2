package org.launchcode.roomranger.controllers;

import org.launchcode.roomranger.data.CommentRepository;
import org.launchcode.roomranger.models.Comment;
import org.launchcode.roomranger.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentControl {
    @Autowired
    private CommentService commentService;
    @PostMapping()
    public ResponseEntity<Comment>createComment(@RequestBody Comment comment, @PathVariable int id){
    return ResponseEntity.ok(new Comment());
    }

    //    @PostMapping("/room/comment")
//    public String addComment(@RequestParam int roomId, @ModelAttribute @Valid Comment newComment){
//        Optional<Room> result = roomRepository.findById(roomId);
//        Room room = result.get();
//        newComment.setRoom(room);
//        newComment.setCreatedDate(LocalDate.now());
//        commentRepository.save(newComment);
//        return "redirect:/rooms";
//    }
}
