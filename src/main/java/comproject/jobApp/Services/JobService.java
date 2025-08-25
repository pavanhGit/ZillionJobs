package comproject.jobApp.Services;

import comproject.jobApp.Repo.CompanyRepo;
import comproject.jobApp.Repo.JobRepo;
//import comproject.jobApp.model.AppliedJob;
import comproject.jobApp.Repo.UserRepository;
import comproject.jobApp.dto.UpdatedJobDTO;
import comproject.jobApp.model.Company;
import comproject.jobApp.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private UserRepository userRepo;


    public List<Job> findAll() {
        return jobRepo.findAll();
    }

    public ResponseEntity<?> createJob(Job job) {
        Long companyid = job.getCompany().getCompanyid();
        Optional<Company> companyOptional = companyRepo.findByCompanyid(companyid);
        if(companyOptional.isPresent()) {
            Company company = companyOptional.get();
            job.setCompany(company);

            Job savedJob = jobRepo.save(job);
            Long jobId = savedJob.getJobId();

            return new ResponseEntity<>("Job with JOB ID="+jobId+" as been posted Successfully", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Check weather ur Company profile is complete", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> findById(Long id) {
        if(jobRepo.existsById(id)){
            return new ResponseEntity <>(jobRepo.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Job NOT FOUND", HttpStatus.NOT_FOUND);
    }
    public String deleteById(Long id) {
        if (jobRepo.existsById(id)) {
            jobRepo.deleteById(id);
            return "Job delete Successfull";
        } else {
            return "Job not found";
        }
    }
   public ResponseEntity<?> updateJob(Long id, UpdatedJobDTO updatedJob) {

        if (jobRepo.existsById(id)) {
            Job jobObj = jobRepo.findById(id).get();
            jobObj.copyForm(updatedJob);
            jobRepo.save(jobObj);
            return new ResponseEntity("Job with id = " + id + " updated Successfully",HttpStatus.OK);
        }
       return new ResponseEntity("Job not found",HttpStatus.NOT_FOUND);
    }


}

