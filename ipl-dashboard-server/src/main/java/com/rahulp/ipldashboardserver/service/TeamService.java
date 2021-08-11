package com.rahulp.ipldashboardserver.service;

import java.util.Set;

public interface TeamService {
    public void addNewTeam();
    public Set<String> getDistinctTeams();
}