package org.launchcode.roomranger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Comment extends AbstractEntity{
    @ManyToOne
    private Room room;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    private LocalDate createdDate;
    @Column(columnDefinition = "TEXT")
    private String text;

    public Comment() {
    }
    public Comment(Room room, User createdBy, String text) {
        this.room = room;
        this.createdBy = createdBy;
        this.text = text;
    }

    public Comment(String text) {
        this.text = text;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
