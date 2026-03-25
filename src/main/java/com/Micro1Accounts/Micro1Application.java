package com.Micro1Accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Tag(
        name=" crud",
        description = " rest api"
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@OpenAPIDefinition(
        info = @Info(
                title = "Account miscro Sevices",
                description = " Api Doc",
                version = "v1",
                contact = @Contact(
                        name =" DEVIN CARLSO",
                        email = "whysoserious@joker.com"
                ),
                license  =@License(
                      name = " dark knight"
)
        )
)
public class Micro1Application {

	public static void main(String[] args) {

        SpringApplication.run(Micro1Application.class, args);
	}

}
