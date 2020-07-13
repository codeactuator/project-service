package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.Workforce;

public interface ProjectCustomRepository {

    public void addResource(Long projectId, Workforce resource);
}
