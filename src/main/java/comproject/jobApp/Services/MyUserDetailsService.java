package comproject.jobApp.Services;

import comproject.jobApp.Repo.UserRepository;
import comproject.jobApp.model.User;
import comproject.jobApp.model.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found..."));
        if(user == null){
            throw new UsernameNotFoundException("user not found.....");
        }
        return new UserPrinciple(user);
    }
}
