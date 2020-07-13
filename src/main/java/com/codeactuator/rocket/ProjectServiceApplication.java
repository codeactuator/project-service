package com.codeactuator.rocket;


import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.dao.RoleRepository;
import com.codeactuator.rocket.dao.WorkforceRepository;
import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.domain.Role;
import com.codeactuator.rocket.domain.Workforce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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

	@Autowired
	private WorkforceRepository workforceRepository;

	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Hello Projector");


		projectRepository.saveAll(Arrays.asList(
			new Project("Foo"),
			new Project("Bar"),
			new Project("Etc")
		));



		projectRepository
				.findAll()
				.forEach(project -> {
					Role role = new Role("Developer");
					roleRepository.save(role);

					Workforce workforce = new Workforce("SHK", "SHEKHAR KUMAR", "shekhar@gmail.com", role);
					workforceRepository.save(workforce);

					projectRepository.addResource(project.getId(), workforce);
				});

		/*
		projectRepository
				.findAll()
				.forEach(System.out::println);

		*/
	}
}
