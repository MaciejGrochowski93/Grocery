package maciej.grochowski.grocerystore.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("maciej.grochowski"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        final String DESCRIPTION = "This is a project of the grocery store. It allows you to log in with user/user, " +
                "admin/admin credentials, or create your own account by registering with your mail. " +
                "USER can buy Products and pay for them, ADMIN can delete, update or create new Products.";

        return new ApiInfo(
                "Maciej's Grocery",
                DESCRIPTION,
                "1.00",
                "https://www.linkedin.com/in/maciej-grochowski-477b62149",
                "Maciej Grochowski",
                "My GitHub",
                "https://github.com/MaciejGrochowski93"
        );
    }
}
