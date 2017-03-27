package org.descartes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@SpringBootApplication
public class ProjetAjdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetAjdApplication.class, args);
	}
	@Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
      ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
      viewResolver.setTemplateEngine(templateEngine);
      return viewResolver;
    }

    @Bean 
    public TemplateEngine templateEngine(TemplateResolver templateResolver) {
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(templateResolver);
      return templateEngine;
    }
    @Bean
    public TemplateResolver templateResolver() {
      TemplateResolver templateResolver = new ServletContextTemplateResolver();
      templateResolver.setPrefix("/WEB-INF/templates");
      templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode("HTML5");
      System.out.println("templateResolver");
      return templateResolver;
    }
}
