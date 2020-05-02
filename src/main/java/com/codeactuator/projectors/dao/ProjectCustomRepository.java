package com.codeactuator.projectors.dao;

import org.springframework.stereotype.Repository;

public interface ProjectCustomRepository {

    public void addResource(Integer projectId, Integer resourceId);
}
