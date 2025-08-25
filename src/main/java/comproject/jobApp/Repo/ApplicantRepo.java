package comproject.jobApp.Repo;

import comproject.jobApp.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepo extends JpaRepository<Applicant, Long> {
    Applicant findByApplicantid(int applicantid);
}
