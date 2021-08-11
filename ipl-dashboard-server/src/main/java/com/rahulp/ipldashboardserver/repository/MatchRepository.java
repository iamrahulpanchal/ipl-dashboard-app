package com.rahulp.ipldashboardserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rahulp.ipldashboardserver.entity.MatchEntity;

@Repository(value = "matchRepository")
public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {

    @Query("select distinct(m.team1) from MatchEntity m")
    public List<String> getDistinctTeam1();

    @Query("select distinct(m.team2) from MatchEntity m")
    public List<String> getDistinctTeam2();

    @Query("select count(m) from MatchEntity m where m.team1 = ?1 OR m.team2 = ?1")
    public Long getTotalMatches(String teamName);

    @Query("select count(m) from MatchEntity m where m.matchWinner = ?1")
    public Long getTotalWins(String teamName);

    @Query("select count(m) from MatchEntity m where m.matchWinner = 'NA' AND (m.team1 = ?1 OR m.team2 = ?1)")
    public Long getNoResult(String teamName);
}