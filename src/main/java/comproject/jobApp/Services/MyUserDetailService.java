//package comproject.jobApp.Services;
//
//import comproject.jobApp.Repo.UserRepository;
//import comproject.jobApp.model.User;
//import comproject.jobApp.model.UserPrincipal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//@Service
//public class MyUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepo.findByName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("User NOT FOUND");
//        }
//        return new UserPrincipal(user);
//    }
//}
