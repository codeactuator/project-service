package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
}