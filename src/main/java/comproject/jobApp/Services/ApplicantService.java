package comproject.jobApp.Services;

import comproject.jobApp.Repo.ApplicantRepo;
import comproject.jobApp.dto.UpdatedJobDTO;
import comproject.jobApp.dto.UpdtApplicantProfile;
import comproject.jobApp.model.Applicant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ApplicantService  {
    @Autowired
    private ApplicantRepo applicantRepo;


    public String completeProfile(Applicant applicant) {
        System.out.println(">>> Name: " + applicant.getApplicantname());
        applicantRepo.save(applicant);
        return "Applicant Profile Completed";
    }


    public ResponseEntity<?> updateProfile(int applicantId, UpdtApplicantProfile profile) {
        Applicant presentApplicant = applicantRepo.findByApplicantid(applicantId);
        if(presentApplicant == null) {
            return new ResponseEntity<>("Applicant not found with ID ",HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(profile, presentApplicant, getNullPropertyNames(profile));
        applicantRepo.save(presentApplicant);

        return new ResponseEntity("Applicant Profile Updated",HttpStatus.OK);
    }
    public String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }



}
