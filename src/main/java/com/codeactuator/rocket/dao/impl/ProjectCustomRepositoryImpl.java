package com.codeactuator.rocket.dao.impl;

import com.codeactuator.rocket.dao.ProjectCustomRepository;
import com.codeactuator.rocket.domain.Project;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ProjectCustomRepositoryImpl implements ProjectCustomRepository {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addResource(Long projectId, Long resourceId) {
        Project project = entityManager.find(Project.class, projectId);
        project.getResources().add(resourceId);
        entityManager.merge(project);
    }
}
