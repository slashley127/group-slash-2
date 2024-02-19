//package org.launchcode.roomranger.authorization;
//
//import jakarta.persistence.*;
//import org.launchcode.roomranger.models.User;
//import jakarta.persistence.Id;
//
//@Entity
//public class Token {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String token;
//
//    private boolean loggedOut;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id") // Assuming the column in the Token table referencing the User is called "user_id"
//    private User user;
//
//    // Constructors, getters, and setters...
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public boolean isLoggedOut() {
//        return loggedOut;
//    }
//
//    public void setLoggedOut(boolean loggedOut) {
//        this.loggedOut = loggedOut;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
