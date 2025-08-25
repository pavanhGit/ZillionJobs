package comproject.jobApp.Controller;

import comproject.jobApp.Services.JobService;
import comproject.jobApp.Services.ReviewService;
import comproject.jobApp.model.Job;
import comproject.jobApp.model.Review;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private JobService jobService;
    @Autowired
    private ReviewService rsrvc;

    @GetMapping("/jobs")
    @Operation(summary = "Get all jobs list")
    public List<Job> findAll() {
        return jobService.findAll();
    }

    @GetMapping("/jobs/{id}")
    @Operation(summary = "Get job by ID")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return jobService.findById(id);
    }


}
