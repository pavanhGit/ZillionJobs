package comproject.jobApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicantid;
    @JsonProperty("applicantname")
    @Column(nullable = false)
    private String applicantname;
    @Column(nullable = false)
    private String phoneno;
    @Column(nullable = false)
    private Date dateofbirth;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String resumeLink;

    public Applicant(int applicantid, String applicantname, String phoneno, Date dateofbirth, int age, String gender, String email, String location, String resumeLink) {
        this.applicantid = applicantid;
        this.applicantname = applicantname;
        this.phoneno = phoneno;
        this.dateofbirth = dateofbirth;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.location = location;
        this.resumeLink = resumeLink;
    }

    public Applicant() {
    }

    public void copyForm(Applicant applicant) {
        this.applicantid = applicant.applicantid;
        this.applicantname = applicant.applicantname;
        this.phoneno = applicant.phoneno;
        this.dateofbirth = applicant.dateofbirth;
        this.age = applicant.age;
        this.gender = applicant.gender;
        this.email = applicant.email;
        this.location = applicant.location;
        this.resumeLink = applicant.resumeLink;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public long getApplicantid() {
        return applicantid;
    }

    public void setApplicantid(int applicantid) {
        this.applicantid = applicantid;
    }

    public String getApplicantname() {
        return applicantname;
    }

    public void setApplicantname(String applicantname) {
        this.applicantname = applicantname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
