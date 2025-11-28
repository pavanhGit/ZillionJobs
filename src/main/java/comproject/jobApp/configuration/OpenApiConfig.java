package comproject.jobApp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                .title("ZillionJObs API")
                        .description("Job search & Recruitment backend APIs")
                        .version("1.0"));
    }
}
