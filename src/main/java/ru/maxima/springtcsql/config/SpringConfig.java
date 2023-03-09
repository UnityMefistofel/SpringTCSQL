package ru.maxima.springtcsql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.maxima.springtcsql")
@PropertySource("classpath:database.properties")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    @Autowired
    Environment environment;
    private final ApplicationContext applicationContext;

    private final String DRIVER = "driver";
    private final String URL = "url";
    private final String USER = "pguser";
    private final String PASSWORD = "pgpass";

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmDataSource = new DriverManagerDataSource();
        dmDataSource.setUrl(environment.getProperty(URL));
        dmDataSource.setUsername(environment.getProperty(USER));
        dmDataSource.setPassword(environment.getProperty(PASSWORD));
        dmDataSource.setDriverClassName(environment.getProperty(DRIVER));
        return dmDataSource;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        registry.viewResolver(resolver);
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8"); // <- this was added
        resolver.setForceContentType(true); // <- this was added
        resolver.setContentType("text/html; charset=UTF-8"); // <- this was added
        registry.viewResolver(resolver);
    }
}
