package com.git.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
@EntityScan(
        basePackageClasses = {GitIntegrationApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class GitIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitIntegrationApplication.class, args);
	}
}
