package comproject.jobApp.Services;

import comproject.jobApp.Repo.JobRepo;
import comproject.jobApp.model.Job;
import comproject.jobApp.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo repo;


    public List<Job> findAll() {
        return repo.findAll();
    }

    public ResponseEntity<?> createJob(List<Job> jobList) {

//        if(jobList.isEmpty() || jobList == null){
//            return new ResponseEntity<>("Yo Job list is Empty or null",HttpStatus.BAD_REQUEST);
//        }
//        for(Job job : jobList){
//            if(job == null){
//                return new ResponseEntity<>("One or more job is null",HttpStatus.BAD_REQUEST);
//            }
//            if(StringUtils.isNullorEmpty(job.getTitle())||
//                    StringUtils.isNullorEmpty(job.getDescription())||
//                    StringUtils.isNullorEmpty(job.getLocation())||
//                    StringUtils.isNullorEmpty(job.getMinsalary())||
//                    StringUtils.isNullorEmpty(job.getMaxsalary())){
//                return new ResponseEntity<>("One of the job fields is empty or null",HttpStatus.BAD_REQUEST);
//            }
//        }

        try {
            repo.saveAll(jobList);
            return new ResponseEntity<>("Jobs saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error while saving job : "+e.getMessage());
            return new ResponseEntity<>("Failed to save jobs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    public ResponseEntity<?> findById(int id) {
        if(repo.existsById(id)){
            return new ResponseEntity <>(repo.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Job NOT FOUND", HttpStatus.NOT_FOUND);

    }


    public String deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Job delete Successfull";
        } else {
            return "Job not found";
        }
    }


    public String updateJob(int id, Job updatedJob) {

        if (repo.existsById(id)) {
            Job jobObj = repo.findById(id).get();
            jobObj.copyFrom(updatedJob);
            repo.save(jobObj);
            return "Job with id = " + id + " updated Successfully";
        }
        return "Job Not Found";
    }
}

