package comproject.jobApp.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    //private long contactNo;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(Long userId, String username, String password, long contactNo, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        //this.contactNo = contactNo;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public long getContactNo() {
//        return contactNo;
//    }
//
//    public void setContactNo(long contactNo) {
//        this.contactNo = contactNo;
//    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
