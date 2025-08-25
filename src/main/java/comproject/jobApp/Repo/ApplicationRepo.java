package comproject.jobApp.Repo;

import comproject.jobApp.model.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepo extends JpaRepository<Applications, Long> {

    List<Applications> findByApplicant_Applicantid(int applicantId);

    Optional<Applications> findByApplicationId(Long applicationId);
}
