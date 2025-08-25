package comproject.jobApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Applications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;
    @ManyToOne
    @JoinColumn(name = "applicantid")
    private Applicant applicant;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    private Status status;
    private LocalDateTime appliedOn;

    public Applications(Long applicationId, Applicant applicant, Job job, Status status, LocalDateTime appliedOn) {
        this.applicationId = applicationId;
        this.applicant = applicant;
        this.job = job;
        this.status = status;
        this.appliedOn = appliedOn;
    }

    public Applications() {}

    public void copyFrom(Applications applications) {
        this.applicationId = applications.applicationId;
        this.applicant = applications.applicant;
        this.job = applications.job;
        this.status = applications.status;
        this.appliedOn = applications.appliedOn;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDateTime appliedOn) {
        this.appliedOn = appliedOn;
    }
}
