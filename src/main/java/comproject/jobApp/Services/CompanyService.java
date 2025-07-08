package comproject.jobApp.Services;

import comproject.jobApp.Repo.CompanyRepo;
import comproject.jobApp.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo repo;

    public List<Company> getAll() {
        return repo.findAll();
    }

    public String save(List<Company> company) {
        List<Company> cmpLst = company.stream().filter(compny -> !repo.existsByName(compny.getName())).collect(Collectors.toList());
        if(cmpLst.isEmpty()){
            return "No new Company added";
        }else{
            repo.saveAll(cmpLst);
            return "List of Companies added";
        }
    }


    public String updateCmp(int id, Company company) {
        if (repo.existsById(id)) {
            Company cmpy = repo.findById(id).get();
            cmpy.copyForm(company);
            repo.save(cmpy);
            return "Company with id " + id + " is Updated successfull";
        }
        return "NOT FOUND";
    }


    public String deleteCmp(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Company with id " + id + " is deleted successfull";
        }
        return "NOT FOUND";
    }

    public ResponseEntity<Company> findById(int id) {
        Optional<Company> cmp = repo.findById(id);
        if (cmp.isPresent()) {
            return new ResponseEntity<>(cmp.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
