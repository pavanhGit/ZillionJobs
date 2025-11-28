package comproject.jobApp.Controller;

import comproject.jobApp.Services.JobService;
import comproject.jobApp.Services.ReviewService;
import comproject.jobApp.model.Job;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private JobService jobService;
    @Autowired
    private ReviewService rsrvc;

    @GetMapping("/home")
    public String displayHomePage(Model model) {
        model.addAttribute("username", "pavan");
        return "home";
    }

    @GetMapping("/jobs")
    @Operation(summary = "Get all jobs", description = "Returns list of available job posting based on filters")
    public Page<Job> getJobs(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "postedDate") String[] sortBy,
                             @RequestParam(defaultValue = "desc") String sortDir
                             ){
        List<Sort.Order> orders = new ArrayList<>();

        for(String sortField : sortBy){
            orders.add(
                    sortDir.equalsIgnoreCase("asc")
                    ? new Sort.Order(Sort.Direction.ASC, sortField)
                            : new Sort.Order(Sort.Direction.DESC, sortField)

            );
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        return jobService.findAll(pageable);
    }
}
