package comproject.jobApp.configuration;

import comproject.jobApp.Services.MyUserDetailsService;
import comproject.jobApp.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(MyUserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        // Public endpoints
                        .requestMatchers("/login", "/register", "/v3/api-docs/**", "/swagger-ui/**", "/public/**",
                                "/error","/swagger-ui.html","/swagger-resources/**").permitAll()
                        // Applicant endpoints
                        .requestMatchers(HttpMethod.GET, "/applicant/applications/{applicantId}").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/applicant/completeprofile").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/applicant/apply").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/applicant/updateprofile").hasRole("APPLICANT")
                        // Recruiter endpoints
                        .requestMatchers(HttpMethod.PUT, "/recruiter/updatestatus/{applicantid}").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.PUT, "/recruiter/updatejob/{id}").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.POST, "/recruiter/jobs").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.GET, "/recruiter/getapplicationbyid/{jobId}").hasRole("RECRUITER")
                        .requestMatchers(HttpMethod.DELETE, "/recruiter/deletejob/{id}").hasRole("RECRUITER")
                        // Any other request
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder()); // reuse the bean
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
