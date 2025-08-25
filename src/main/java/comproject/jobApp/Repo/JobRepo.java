package comproject.jobApp.Repo;

import comproject.jobApp.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalLong;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

}