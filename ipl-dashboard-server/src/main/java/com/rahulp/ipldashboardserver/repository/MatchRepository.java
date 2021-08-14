package com.rahulp.ipldashboardserver.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
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
    
    @Query("select m from MatchEntity m where m.team1 = ?1 or m.team2 = ?1 ORDER BY m.date DESC")
    public List<MatchEntity> getFirst4Matches(String teamName, Pageable pageable);
    
    @Query("select m from MatchEntity m where (m.team1 = ?1 or m.team2 = ?1) AND (m.date >= ?2 AND m.date < ?3) ORDER BY m.date DESC")
    public List<MatchEntity> getMatchesByTeamAndYear(String teamName, LocalDate date1, LocalDate date2);
    
    @Query("select m.date from MatchEntity m where m.team1 = ?1 or m.team2 = ?1 ORDER BY m.date DESC")
    public List<LocalDate> getMatchesDate(String teamName);
}