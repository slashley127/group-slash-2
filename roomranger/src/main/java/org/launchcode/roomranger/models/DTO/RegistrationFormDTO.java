package org.launchcode.roomranger.models.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RegistrationFormDTO {

        @NotBlank(message = "First Name is required")
        @Size(max = 50, message = "Name is too long")
        private String firstName;

        @NotBlank(message = "Last Name is required")
        @Size(max = 50, message = "Last Name is too long")
        private String lastName;

        @NotBlank(message = "Date of Birth is required")
        private String dob;

        @NotBlank(message = "Username is required")
        private static String username;

        @NotBlank(message = "Password is required")
        private static String password;

        @NotBlank(message = "Confirm Password is required")
        private String confirmPassword;

        @NotBlank(message = "Role is required")
        private String role;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        // Getters and setters...

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public static String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public static String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

