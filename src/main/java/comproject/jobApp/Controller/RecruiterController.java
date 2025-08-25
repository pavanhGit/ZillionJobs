package comproject.jobApp.Controller;

import comproject.jobApp.Services.ApplicationsService;
import comproject.jobApp.Services.CompanyService;
import comproject.jobApp.Services.JobService;
import comproject.jobApp.dto.UpdatedJobDTO;
import comproject.jobApp.model.Company;
import comproject.jobApp.model.Job;
import comproject.jobApp.model.Status;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private JobService jobService;
    @Autowired
    private ApplicationsService applicationsService;
    @Autowired
    private CompanyService companyService;

    @PostMapping("/completeprofile")
    @Operation(summary = "Complete the recruiter/Company profile")
    public ResponseEntity<?> completeProfile(@RequestBody Company company) {
        return companyService.completeProfile(company);
    }

    @PostMapping("/jobs")
    @Operation(summary = "Post a new Job")
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @DeleteMapping("/deletejob/{id}")
    @Operation(summary = "Delete the job")
    public ResponseEntity<?> deleteJob(@PathVariable Long id){
        String deleted = jobService.deleteById(id);
        if(deleted != null){
            return new ResponseEntity<>("Job is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/updatejob/{id}")
    @Operation(summary = "Update the job")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody UpdatedJobDTO updatedJob){
        return jobService.updateJob(id, updatedJob);
    }

    @GetMapping("/getapplicationbyid/{jobId}")
    @Operation(summary = "Get Application by JobID")
    public ResponseEntity<?> getapplicationbyid(@PathVariable Long jobId){
        return applicationsService.getapplicationbyid(jobId);
    }

    @PutMapping("/updatestatus/{applicantid}")
    @Operation(summary = "Update the Application Status")
    public ResponseEntity<?> updateStatus(@PathVariable Long applicantid, @RequestBody Status status){
        return applicationsService.updateStatus(applicantid, status);
    }
}
