package com.rahulp.ipldashboardserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rahulp.ipldashboardserver.entity.TeamEntity;

public interface TeamRepository extends CrudRepository<TeamEntity, String> {

    @Query("select t.teamName from TeamEntity t")
    public List<String> getAllTeams();
}