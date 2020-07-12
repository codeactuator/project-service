package com.codeactuator.rocket.dao;


import com.codeactuator.rocket.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long>, ProjectCustomRepository {


}
