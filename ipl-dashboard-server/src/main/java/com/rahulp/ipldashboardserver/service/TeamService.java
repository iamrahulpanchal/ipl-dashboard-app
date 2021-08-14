package com.rahulp.ipldashboardserver.service;

import java.util.List;
import java.util.Set;

import com.rahulp.ipldashboardserver.entity.MatchEntity;
import com.rahulp.ipldashboardserver.entity.TeamEntity;

public interface TeamService {
    public void addNewTeam();
    public Set<String> getDistinctTeams();
    public TeamEntity getTeamData(String teamName);
    public List<String> getAllTeams();
    public MatchEntity getMatchesByTeamAndYear(String teamName, Integer year);
}