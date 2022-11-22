package com.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graphql.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
