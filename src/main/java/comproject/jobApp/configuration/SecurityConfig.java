package comproject.jobApp.configuration;


import comproject.jobApp.Services.MyUserDetailsService;
import comproject.jobApp.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

    private MyUserDetailsService userDetailsService;
    private JwtFilter jwtFilter;

    public SecurityConfig(MyUserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
        return http
                .csrf(customiser -> customiser.disable())
                .authorizeHttpRequests(request -> 
                request.requestMatchers("/login", "/register", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/applicant/**").hasRole("APPLICANT")
                .requestMatchers("/recruiter/**").hasRole("RECRUITER")
                		.anyRequest().authenticated())
////                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
                .httpBasic(httpBasic -> httpBasic.disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider())
                .build();

    }
    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    public JwtFilter jwtFilter(JwtService jwtService, MyUserDetailsService userDetailsService) {
//        return new JwtFilter(jwtService, userDetailsService);
//    }

}
