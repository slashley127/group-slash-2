package org.launchcode.roomranger.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    private AssignedRoom assignedRoom;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private String createdBy;

    private LocalDateTime createdDate;
    @Column(columnDefinition = "TEXT")
    private String text;

    public Comment() {
    }
    public Comment(AssignedRoom assignedRoom, String createdBy, String text) {
        this.assignedRoom = assignedRoom;
        this.createdBy = createdBy;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comment(String text) {
        this.text = text;
    }

    public AssignedRoom getAssignedRoom() {
        return assignedRoom;
    }

    public void setAssignedRoom(AssignedRoom assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
