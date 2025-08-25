package comproject.jobApp.Repo;

import comproject.jobApp.model.Company;
import comproject.jobApp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
    boolean existsByCompanyname(String companyname);

    Optional<Company> findByCompanyid(Long companyId);


}
