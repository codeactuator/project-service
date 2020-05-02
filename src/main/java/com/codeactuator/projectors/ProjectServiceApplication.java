package com.codeactuator.projectors;


import com.codeactuator.projectors.dao.ProjectRepository;
import com.codeactuator.projectors.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
//@ConfigurationProperties
@EnableHystrix
@EnableHystrixDashboard
public class ProjectServiceApplication implements ApplicationRunner {


	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Autowired
	private ProjectRepository projectRepository;


	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Hello Projector");


		projectRepository.saveAll(Arrays.asList(
			new Project(null, "CSA", null),
			new Project(null, "Lending Point", null),
			new Project(null, "Virtual Agent", null)
		));


		projectRepository
				.findAll()
				.forEach(project -> {
					//resourceRepository.addResource(project.getId(), 1);
					projectRepository.addResource(project.getId(), 1);
				});

		projectRepository
				.findAll()
				.forEach(System.out::println);

	}
}
