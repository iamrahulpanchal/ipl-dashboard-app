package com.rahulp.ipldashboardserver.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rahulp.ipldashboardserver.entity.TeamEntity;
import com.rahulp.ipldashboardserver.repository.MatchRepository;
import com.rahulp.ipldashboardserver.repository.TeamRepository;

@Service(value = "teamService")
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void addNewTeam() {

        Set<String> teamsList = getDistinctTeams();
        List<TeamEntity> saveTeams = new ArrayList<>();

        for (String teamVal: teamsList) {
            Long totalMatches = matchRepository.getTotalMatches(teamVal);
            Long totalWins = matchRepository.getTotalWins(teamVal);
            Long noResult = matchRepository.getNoResult(teamVal);
            Long losses = totalMatches - totalWins - noResult;

            TeamEntity team = new TeamEntity();
            team.setTeamName(teamVal);
            team.setTotalMatches(totalMatches);
            team.setWins(totalWins);
            team.setLosses(losses);
            team.setNoResult(noResult);

            saveTeams.add(team);
        }

        teamRepository.saveAll(saveTeams);
    }

    @Override
    public Set<String> getDistinctTeams() {
        List<String> team1List = matchRepository.getDistinctTeam1();
        List<String> team2List = matchRepository.getDistinctTeam2();

        Set<String> teamsList = new LinkedHashSet<>();
        teamsList.addAll(team1List);
        teamsList.addAll(team2List);

        return teamsList;
    }

	@Override
	public TeamEntity getTeamData(String teamName) {
		Optional<TeamEntity> optionalTeam = teamRepository.findById(teamName);
		TeamEntity team = optionalTeam.orElse(null);
		Pageable first4 = PageRequest.of(0, 4);
		team.setFirst4Matches(matchRepository.getFirst4Matches(teamName, first4));
		return team;
	}

	@Override
	public List<String> getAllTeams() {
		List<String> teamsList = teamRepository.getAllTeams();
		return teamsList;
	}
    
    

}