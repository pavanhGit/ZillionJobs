package comproject.jobApp.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String password;
    //private long contactNo;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(Long userId, String name, String password, long contactNo, Role role) {
        this.userId = userId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
