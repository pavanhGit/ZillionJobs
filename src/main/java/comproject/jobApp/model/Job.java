package comproject.jobApp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import comproject.jobApp.dto.UpdatedJobDTO;
import jakarta.persistence.*;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String position;
    private String description;
    private String location;
    private String minSalary;
    private String maxSalary;

    @JsonIgnoreProperties({"jobs", "reviews"})
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id", nullable = false) // FK column in Job table
    private Company company;

    public void copyForm(Job other) {
        if (other == null) {
            return;
        }
        this.jobId = other.jobId;
        this.position = other.position;
        this.description = other.description;
        this.location = other.location;
        this.minSalary = other.minSalary;
        this.maxSalary = other.maxSalary;
        this.company = other.company; // shallow copy of company
    }
    public void copyForm(UpdatedJobDTO otherjob){
        this.position = otherjob.getPosition();
        this.description = otherjob.getDescription();
        this.location = otherjob.getLocation();
        this.minSalary = otherjob.getMinSalary();
        this.maxSalary = otherjob.getMaxSalary();
    }

    public Job() {
    }

    public Job(Long jobId, String position, String description, String location, String minSalary, String maxSalary, Company company) {
        this.jobId = jobId;
        this.position = position;
        this.description = description;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.company = company;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
