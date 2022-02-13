package hu.uni.miskolc.teszteles.beadando.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"hu.uni.miskolc.teszteles.beadando.*"})
@EntityScan(basePackages = {"hu.uni.miskolc.teszteles.beadando.*"})
@EnableJpaRepositories(basePackages = {"hu.uni.miskolc.teszteles.beadando.*"})
public class DemoApp {

    public static void main(String[] args) {

        SpringApplication.run(DemoApp.class);
    }
}
