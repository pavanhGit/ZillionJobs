package comproject.jobApp.Services;

import comproject.jobApp.Repo.ApplicantRepo;
import comproject.jobApp.Repo.ApplicationRepo;
import comproject.jobApp.Repo.JobRepo;
import comproject.jobApp.dto.ApplicationFromDTO;
import comproject.jobApp.model.Applicant;
import comproject.jobApp.model.Job;
import comproject.jobApp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import comproject.jobApp.model.Applications;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationsService {

    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private ApplicantRepo applicantRepo;
    @Autowired
    private JobRepo jobrepo;

    public ResponseEntity<?> getApplicationsByApplicantId(int applicantId) {
        List<Applications> listapp = applicationRepo.findByApplicant_Applicantid(applicantId);
        if(listapp.isEmpty()){
            return new ResponseEntity<>("No Application with this ID", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listapp,HttpStatus.OK);
    }

    public ResponseEntity<?> applyForJob(ApplicationFromDTO dto) {
        Job job = jobrepo.findById(dto.getJobId())
                .orElseThrow(()-> new RuntimeException("No jobs with ID: "+dto.getJobId()));

        Applications applications = new Applications();
        applications.setJob(job);
        applications.setAppliedOn(LocalDateTime.now());
        applications.setStatus(Status.APPLIED);

        Applicant applicant = new Applicant();
        applicant.setApplicantname(dto.getApplcantName());
        applicant.setEmail(dto.getApplicantEmail());
        applicant.setResumeLink(dto.getResumeLink());
        applications.setApplicant(applicant);
        applicantRepo.save(applicant);

        return new ResponseEntity<>("Job applied Successfully",HttpStatus.OK);
    }

    public ResponseEntity<?> getapplicationbyid(Long jobId) {
        Optional<Applications> applications = applicationRepo.findById(jobId);
        if(applications.isPresent()){
            return new ResponseEntity<>(applications.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Application not found with ID: " + jobId, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateStatus(Long applicationid, Status status) {
        Applications application = applicationRepo
                .findByApplicationId(applicationid)
                .orElseThrow(()->new RuntimeException("No applications with that ID"));
        application.setStatus(status);
        applicationRepo.save(application);
        return new ResponseEntity<>("Application updated successfully", HttpStatus.OK);

    }
}
