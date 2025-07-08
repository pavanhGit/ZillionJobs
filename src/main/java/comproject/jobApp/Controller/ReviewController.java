package comproject.jobApp.Controller;

import comproject.jobApp.Services.ReviewService;
import comproject.jobApp.dto.ReviewDto;
import comproject.jobApp.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService rsrvc;

    @GetMapping("/{companyId}/reviews")
    public ResponseEntity<?> getAllReview(@PathVariable int companyId) {
        return rsrvc.findByCompId(companyId);
    }
    
    @GetMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable int companyId, 
            @PathVariable int reviewId) {
        return rsrvc.getReviewById(companyId, reviewId);
    }

    @PostMapping("/{companyId}/reviews")
    public ResponseEntity<String> postReview(
            @PathVariable int companyId, 
            @RequestBody ReviewDto reviewDto) {
        return rsrvc.postReview(companyId, reviewDto);
    }

    @PutMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable int companyId, 
            @PathVariable int reviewId, 
            @RequestBody Review review) {
        return rsrvc.updateReview(companyId, reviewId, review);
    }

    @DeleteMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable int companyId, @PathVariable int reviewId){
        return rsrvc.deleteReview(companyId, reviewId);
    }
}
