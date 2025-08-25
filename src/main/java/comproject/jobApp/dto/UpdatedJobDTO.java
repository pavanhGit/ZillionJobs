package comproject.jobApp.dto;

import comproject.jobApp.model.Company;
import comproject.jobApp.model.Job;

public class UpdatedJobDTO {
    private String position;
    private String description;
    private String location;
    private String minSalary;
    private String maxSalary;

    public UpdatedJobDTO(String position, String description, String location, String minSalary, String maxSalary) {
        this.position = position;
        this.description = description;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
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
}
