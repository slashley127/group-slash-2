package org.launchcode.roomranger.models.Dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Integer id;
    private int assignRoomId;
    private String text;
    private String user;
    private LocalDateTime createdDate;

    public CommentDto(int assignRoomId, String text, String user) {
        this.assignRoomId = assignRoomId;
        this.text = text;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getAssignRoomId() {
        return assignRoomId;
    }

    public void setAssignRoomId(int assignRoomId) {
        this.assignRoomId = assignRoomId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "assignRoomId=" + assignRoomId +
                ", text='" + text + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
