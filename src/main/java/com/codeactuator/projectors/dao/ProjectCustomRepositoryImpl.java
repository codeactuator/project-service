package com.codeactuator.projectors.dao;

import com.codeactuator.projectors.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addResource(Integer projectId, Integer resourceId) {
        Project project = entityManager.find(Project.class, projectId);
        project.getResources().add(resourceId);
        entityManager.merge(project);
    }
}
