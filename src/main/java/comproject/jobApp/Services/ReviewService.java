package comproject.jobApp.Services;

import comproject.jobApp.Repo.CompanyRepo;
import comproject.jobApp.Repo.ReviewRepo;
import comproject.jobApp.dto.ReviewDto;
import comproject.jobApp.model.Company;
import comproject.jobApp.model.Review;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo rrepo;

    @Autowired
    private CompanyRepo companyRepo;

    public ResponseEntity<?> findByCompId(int companyId) {
        if(companyRepo.existsById(companyId)){
            List<Review> reviews = rrepo.getAllByCompany(companyRepo.findById(companyId).get());
            if (reviews.isEmpty()) {
                return new ResponseEntity<>("No reviews for the company",HttpStatus.OK);
            }else{
                return new ResponseEntity<>(reviews,HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>("Company Not Found",HttpStatus.BAD_REQUEST);
        }

//        Optional<Company> companyOpt = companyRepo.findByCompId(companyId);
//
//        if (companyOpt.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Company company = companyOpt.get();
//        List<Review> reviews = company.getReviews();
//
//        if (reviews == null || reviews.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // or NOT_FOUND if you prefer
//        }
//
//        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }


//    public ResponseEntity<List<Review>> findByCompId(int companyId) {
//        Optional<Company> company = companyRepo.findByCompId(companyId);
//        if (company.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(company.get().getReviews(),HttpStatus.OK );
//    }

    public ResponseEntity<String> postReview(int companyId, ReviewDto reviewDto) {
        if (reviewDto == null) {
            return new ResponseEntity<>("Review cannot be null", HttpStatus.BAD_REQUEST);
        }
        Optional<Company> company = companyRepo.findById(companyId);
        if (company.isEmpty()) {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
        Review review = new Review();
        review.setTitle(reviewDto.getTitle());
        review.setDescription(reviewDto.getDescription());
        review.setRating(reviewDto.getRating());
        review.setCompany(companyRepo.findByCompId(companyId).get());
        rrepo.save(review);
        return new ResponseEntity<>("Review posted successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateReview(int companyId, int reviewId, @Valid Review review) {
        if (review == null) {
            return new ResponseEntity<>("Review cannot be null", HttpStatus.BAD_REQUEST);
        }
        
        Optional<Company> company = companyRepo.findById(companyId);
        Optional<Review> existingReview = rrepo.findById(reviewId);
        
        if (company.isEmpty() || existingReview.isEmpty()) {
            return new ResponseEntity<>("Company or review not found", HttpStatus.NOT_FOUND);
        }
        
        // Verify the review belongs to the specified company
        if (existingReview.get().getCompany().getCompId() != companyId) {
            return new ResponseEntity<>("Review does not belong to the specified company", HttpStatus.BAD_REQUEST);
        }
        
        Review reviewToUpdate = existingReview.get();
        reviewToUpdate.copyFrom(review);
        rrepo.save(reviewToUpdate);
        
        return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteReview(int companyId, int reviewId) {
        Optional<Review> review = rrepo.findById(reviewId);
        
        if (review.isEmpty()) {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
        
        // Verify the review belongs to the specified company
        if (review.get().getCompany().getCompId() != companyId) {
            return new ResponseEntity<>("Review does not belong to the specified company", HttpStatus.BAD_REQUEST);
        }
        
        rrepo.deleteById(reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<Review> getReviewById(int companyId, int reviewId) {
        Optional<Review> review = rrepo.findById(reviewId);
        
        if (review.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Verify the review belongs to the specified company
        if (review.get().getCompany().getCompId() != companyId) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(review.get(), HttpStatus.OK);
    }


}
