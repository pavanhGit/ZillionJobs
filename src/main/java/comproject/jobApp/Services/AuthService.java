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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    private JwtService jwtService;
    private AuthenticationManager authManager;

    public AuthService() {
    }

    @Autowired
    public AuthService(UserRepository userRepo, BCryptPasswordEncoder encoder, JwtService jwtService, AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
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
            System.out.println("authManager: " + authManager);
            System.out.println("Trying to authenticate user: " + authRequest.getUsername());
            Authentication authentication =
                    authManager.authenticate(
                            new UsernamePasswordAuthenticationToken( authRequest.getUsername(), authRequest.getPassword()));
            System.out.println("Is Authenticated? " + authentication.isAuthenticated());
            if(authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //Optional<User> user1= userRepo.findByUsername(authRequest.getUsername());
                String token = jwtService.generateToken(authRequest.getUsername());
                return new ResponseEntity<>("You Logged in Successfully Token : "+ token,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Something went wrong", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Check your credentials",HttpStatus.UNAUTHORIZED);
        }
    }
}

