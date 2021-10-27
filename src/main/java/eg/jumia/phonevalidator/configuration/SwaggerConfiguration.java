package eg.jumia.phonevalidator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private static final String TITLE = " PhoneNumberValidator REST API";
    private static final String DESCRIPTION = "Allow users to filter and find valid/invalid Phone number  ";
    private static final String VERSION = "API 1.0.0";
    private static final String CONTACT_PERSON_NAME = "Sherin Medhat";
    private static final String CONTACT_PERSON_EMAIL = "shery.m.1991@gmail.com/sherin.medhat@yahoo.com";

    @Bean
    public Docket api() {


        return new Docket(DocumentationType.SWAGGER_2)

                .select()
                .apis(RequestHandlerSelectors.basePackage("eg.jumia.phonevalidator.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(TITLE,DESCRIPTION, VERSION, "",
                new Contact(CONTACT_PERSON_NAME, "", CONTACT_PERSON_EMAIL), "","",new ArrayList<>());
    }
}
