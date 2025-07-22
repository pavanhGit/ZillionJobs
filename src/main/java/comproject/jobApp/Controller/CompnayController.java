package comproject.jobApp.Controller;

import comproject.jobApp.Services.CompanyService;
import comproject.jobApp.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applicant")
public class CompnayController {

    @Autowired
    private CompanyService srvc;

    @GetMapping("/company")
    public List<Company> getAllCompanies() {
        return srvc.getAll();
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        return srvc.findById(id);
    }

    @PostMapping("/company")
    public ResponseEntity<String> addCompany(@RequestBody List<Company> cmp) {
        return new ResponseEntity<>(srvc.save(cmp),HttpStatus.CREATED);
    }

    @PutMapping("/company/{id}")
    public String updateCompany(@PathVariable int id, @RequestBody Company company) {
        return srvc.updateCmp(id, company);
    }

    @DeleteMapping("/company/{id}")
    public String deleteCompany(@PathVariable int id) {
        return srvc.deleteCmp(id);
    }


}
