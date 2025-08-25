package comproject.jobApp.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long companyid;
    @Column(nullable = false)
    private String companyname;
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String recruitername;
    private String industry;
    private Date foundedon;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;


    public void copyFrom(Company other) {
        if (other == null) {
            return;
        }
        this.companyid = other.companyid;
        this.companyname = other.companyname;
        this.description = other.description;
        this.location = other.location;
        this.recruitername = other.recruitername;
        this.industry = other.industry;
        this.foundedon = other.foundedon;
        this.email = other.email;
        this.phone = other.phone;
        this.website = other.website;
        this.jobs = other.jobs;       // shallow copy (same reference)
        this.reviews = other.reviews; // shallow copy
    }

    public Company() {
    }

    public Company(long companyid, String companyname, String description, String location, String recruitername, String industry, Date foundedon, String email, String phone, String website, List<Job> jobs, List<Review> reviews) {
        this.companyid = companyid;
        this.companyname = companyname;
        this.description = description;
        this.location = location;
        this.recruitername = recruitername;
        this.industry = industry;
        this.foundedon = foundedon;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.jobs = jobs;
        this.reviews = reviews;
    }

    public long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(long companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRecruitername() {
        return recruitername;
    }

    public void setRecruitername(String recruitername) {
        this.recruitername = recruitername;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getFoundedon() {
        return foundedon;
    }

    public void setFoundedon(Date foundedon) {
        this.foundedon = foundedon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
