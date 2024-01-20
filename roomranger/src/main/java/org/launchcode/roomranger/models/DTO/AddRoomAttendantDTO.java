package org.launchcode.roomranger.models.DTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;


public class AddRoomAttendantDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @NotBlank(message = "First name is required")
    @Column(length = 30)
    private String firstName;
    @NotBlank(message = "Last name is required")
    private static String lastName;
    private String WorkingDays;
    private String Gender;
    private String PhoneNumber;


    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Column(unique = true)
    private String Email;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkingDays() {
        return WorkingDays;
    }

    public void setWorkingDays(String workingDays) {
        WorkingDays = workingDays;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 30 characters.")
    private static String username;

    @NotBlank
    @Size(min = 5, max = 20, message = "Invalid password. Must be between 5 and 30 characters.")
    private static String password;

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

}
