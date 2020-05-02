package com.codeactuator.projectors.dao;

import com.codeactuator.projectors.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer>, ProjectCustomRepository {


}
