package com.ashwin.java;

import com.ashwin.java.domain.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Configuration
@EnableSwagger2
public class SwaggerDemoConfiguration {
    @Autowired
    BuildProperties buildProperties;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Quote getQuote() {
        List<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote(1L, "Winner don't do different things, they do things differently", "Author One"));
        quotes.add(new Quote(2L, "To get to the heaven, one needs to die first.", "Author Two"));
        quotes.add(new Quote(3L, "Failures are stepping stone to success.", "Author Three"));
        quotes.add(new Quote(4L, "Whether you think you can or cannot, you are right.", "Author Four"));
        quotes.add(new Quote(5L, "Mice play when cats away.", "Author Five"));

        int i = new Random().nextInt(quotes.size());
        return quotes.get(i);
    }

    @Bean
    public Docket swaggerApiCinfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.ashwin.java"))
                .build()
                .apiInfo(new ApiInfo(
                        "Quotes API",
                        "Collection of quotes.",
                        buildProperties.getVersion(),
                        "https://quotes/privacy.html",
                        new Contact("Quotes", "https://www.quotes.com", "myemail@gmail.com"),
                        "MIT",
                        "https://mitlicenseurl.com",
                        Collections.EMPTY_LIST
                ));
    }
}
