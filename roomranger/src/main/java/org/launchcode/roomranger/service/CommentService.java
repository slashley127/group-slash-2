package org.launchcode.roomranger.service;

import org.launchcode.roomranger.data.AssignedRoomRepository;
import org.launchcode.roomranger.data.CommentRepository;
import org.launchcode.roomranger.models.AssignedRoom;
import org.launchcode.roomranger.models.Comment;
import org.launchcode.roomranger.models.Dto.CommentDto;
import org.launchcode.roomranger.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AssignedRoomRepository assignedRoomRepository;
    public Comment save(CommentDto commentDto) {
        //here also need to pass the user authentication param
        Comment comment = new Comment();
        AssignedRoom assignedRoom = assignedRoomRepository.findById(commentDto.getAssignRoomId()).get();
        comment.setAssignedRoom(assignedRoom);
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        if (comment.getId()==null){
            comment.setCreatedDate(LocalDateTime.now());
        }else {
            comment.setCreatedDate(commentDto.getCreatedDate());
        }
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    public List<Comment> getCommentsByAssignedRoomId(int assignRoomId) {
        List<Comment> comments = commentRepository.findAllByAssignedRoomId(assignRoomId);
        return comments;
    }
}
