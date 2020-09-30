package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long>{
}