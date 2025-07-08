package comproject.jobApp.Controller;


import comproject.jobApp.Services.JobService;
import comproject.jobApp.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> createJob(@RequestBody List<Job> job) {
        return jobService.createJob(job);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return jobService.findById(id);

    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable int id){
        String deleted = jobService.deleteById(id);
        if(deleted != null){
            return new ResponseEntity<>("Job is deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/job/{id}")
    public ResponseEntity<?> updateJob(@PathVariable int id, @RequestBody Job updatedJob){
        String updated = jobService.updateJob(id, updatedJob);
        if(updated != null){
            return new ResponseEntity<>("Job with id = "+id+"is updated",HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
