package comproject.jobApp.Controller;

import comproject.jobApp.Services.ApplicantService;
import comproject.jobApp.Services.ApplicationsService;
import comproject.jobApp.Services.ReviewService;
import comproject.jobApp.dto.ApplicationFromDTO;
import comproject.jobApp.dto.UpdtApplicantProfile;
import comproject.jobApp.model.Applicant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ApplicationsService applicationsService;
    @Autowired
    private ApplicantService applicantService;

    @PostMapping("/completeprofile")//working✅
    @Operation(summary = "Complete applicant profile")
    public String completeProfile(@RequestBody Applicant applicant){
        System.out.println("applicant:"+applicant);
        return applicantService.completeProfile(applicant);
    }

    @PutMapping("/updateprofile/{applicantId}")//working✅
    @Operation(summary = "Update the applicant profile")
    public ResponseEntity<?> updateProfile(@RequestParam int applicantId,
                                           @RequestBody UpdtApplicantProfile applicant){
        return applicantService.updateProfile(applicantId, applicant);
    }

    @GetMapping("/applications/{applicantId}")
    @Operation(summary = "Get application by Applicant ID")
    public ResponseEntity<?> getApplicationByUsername(@PathVariable int applicantId){
        return applicationsService.getApplicationsByApplicantId(applicantId);
    }

    @PostMapping("/apply")//working✅
    @Operation(summary = "Apply for Job")
    public ResponseEntity<?> applyForJob(@RequestBody ApplicationFromDTO dto) {
        return applicationsService.applyForJob(dto);
    }

}
