package comproject.jobApp.Services;

import comproject.jobApp.Repo.UserRepository;
import comproject.jobApp.dto.AuthRequest;
import comproject.jobApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    private AuthenticationManager authManager;

    public AuthService(UserRepository userRepo, AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.authManager = authManager;
    }

    public ResponseEntity<String> save(User user) {
        Optional<User> user1 = userRepo.findByUsername(user.getUsername());
        if(user1.isEmpty()){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);

            return new ResponseEntity<>("Registration Successfull", HttpStatus.OK);
        }return new ResponseEntity<>("User Already registered",HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<String> login(AuthRequest authRequest) {

        try {
            Authentication authentication =
                    authManager.authenticate(
                            new UsernamePasswordAuthenticationToken( authRequest.getUsername(), authRequest.getPassword()));
            if(authentication.isAuthenticated()){
                Optional<User> user1= userRepo.findByUsername(authRequest.getUsername());
                return new ResponseEntity<>("You Logged in Successfully",HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Something went wrong", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Check your credentials",HttpStatus.UNAUTHORIZED);
        }
    }
}

