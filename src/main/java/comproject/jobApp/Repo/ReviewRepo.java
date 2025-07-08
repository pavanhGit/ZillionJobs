package comproject.jobApp.Repo;

import comproject.jobApp.model.Company;
import comproject.jobApp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {

    List<Review> getAllByCompany(Company company);
}
