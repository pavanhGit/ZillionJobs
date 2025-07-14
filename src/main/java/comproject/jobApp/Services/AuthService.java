package comproject.jobApp.Services;

import comproject.jobApp.Repo.UserRepository;
import comproject.jobApp.dto.AuthRequest;
import comproject.jobApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public ResponseEntity<String> save(User user) {
        Optional<User> user1 = userRepo.findByName(user.getName());
        if(user1.isEmpty()){
            userRepo.save(user);
            //user.setPassword(encoder.encode(user.getPassword()));
            return new ResponseEntity<>("Registration Successfull", HttpStatus.ACCEPTED);
        }return new ResponseEntity<>("User Already registered",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> login(AuthRequest authRequest) {
        Optional<User> user1= userRepo.findByName(authRequest.getUsername());
        if(user1.isPresent()){
            if(user1.get().getName().equals(authRequest.getUsername())
                    && user1.get().getPassword().equals(authRequest.getPassword()))
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }return new ResponseEntity<>("Check your credentials",HttpStatus.NOT_FOUND);
    }
}

