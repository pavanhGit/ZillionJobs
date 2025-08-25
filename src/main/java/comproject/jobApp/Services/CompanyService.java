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
    private CompanyRepo companyRepo;


    public List<Company> getAll() {
        return companyRepo.findAll();
    }

    public String save(List<Company> company) {
        List<Company> cmpLst = company.stream().filter(compny -> !companyRepo.existsByCompanyname(compny.getCompanyname())).collect(Collectors.toList());
        if(cmpLst.isEmpty()){
            return "No new Company added";
        }else{
            companyRepo.saveAll(cmpLst);
            return "List of Companies added";
        }
    }


//    public String update(int id, Company company) {
//        if (companyRepo.existsById(id)) {
//            Company cmpy = companyRepo.findById(id).get();
//            cmpy.copyForm(company);
//            companyRepo.save(cmpy);
//            return "Company with id " + id + " is Updated successfull";
//        }
//        return "NOT FOUND";
//    }


    public String deleteCmp(int id) {
        if (companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
            return "Company with id " + id + " is deleted successfull";
        }
        return "NOT FOUND";
    }

    public ResponseEntity<Company> findById(int id) {
        Optional<Company> cmp = companyRepo.findById(id);
        if (cmp.isPresent()) {
            return new ResponseEntity<>(cmp.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> completeProfile(Company company) {
        if(company != null) {
            companyRepo.save(company);
            return new ResponseEntity<>("profile saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("profile not saved",HttpStatus.NOT_FOUND);

    }
}
