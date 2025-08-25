package comproject.jobApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class ApplicationFromDTO {
    @NotBlank(message = "Applicant name is required")
    private String applcantName;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Applicant email is required")
    private String applicantEmail;
    @NotBlank(message = "Resume link is required")
    private String resumeLink;
    @NotNull(message = "Job ID is required")
    private Long jobId;
    @NotNull(message = "Date is required")
    private Date appliedOn;

    public ApplicationFromDTO(String applcantName, String applicantEmail, String resumeLink, Long jobId, Date appliedOn) {
        this.applcantName = applcantName;
        this.applicantEmail = applicantEmail;
        this.resumeLink = resumeLink;
        this.jobId = jobId;
        this.appliedOn = appliedOn;
    }

    public String getApplcantName() {
        return applcantName;
    }

    public void setApplcantName(String applcantName) {
        this.applcantName = applcantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }
}
