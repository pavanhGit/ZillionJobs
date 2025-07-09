package comproject.jobApp.model;

import jakarta.persistence.Entity;

@Entity
public class Applicant {
    private long applicantID;
    private String contactNo;
    private String resumePath;

    public Applicant() {
    }

    public Applicant(long applicantID, String contactNo, String resumePath) {
        this.applicantID = applicantID;
        this.contactNo = contactNo;
        this.resumePath = resumePath;
    }

    public long getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(long applicantID) {
        this.applicantID = applicantID;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

}
