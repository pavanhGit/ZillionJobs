package comproject.jobApp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String location;
    private String minSalary;
    private String maxSalary;

    @JsonIgnoreProperties({"jobs", "reviews"})
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false) // FK column in Job table
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job(){

    }

    public Job(int id, String maxSalary, String title, String description, String location, String minSalary) {
        this.id = id;
        this.maxSalary = maxSalary;
        this.title = title;
        this.description = description;
        this.location = location;
        this.minSalary = minSalary;
    }


    public void copyFrom(Job other) {
        if (other == null) return;
        this.maxSalary = other.maxSalary;
        this.title = other.title;
        this.description = other.description;
        this.location = other.location;
        this.minSalary = other.minSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
