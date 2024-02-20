package org.launchcode.roomranger.models.Dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Integer id;
    private int assignRoomId;
    private String text;
    private String createdBy;
    private LocalDateTime createdDate;

    public CommentDto(int assignRoomId, String text, String createdBy) {
        this.assignRoomId = assignRoomId;
        this.text = text;
        this.createdBy = createdBy;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "assignRoomId=" + assignRoomId +
                ", text='" + text + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
