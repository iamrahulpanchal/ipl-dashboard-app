package com.rahulp.ipldashboardserver.service;

import java.time.LocalDate;
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

import com.rahulp.ipldashboardserver.entity.MatchEntity;
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
	
	@Override
	public List<Integer> getYearsListByTeam(String teamName) {
		List<LocalDate> dates = matchRepository.getMatchesDate(teamName);
		List<Integer> yearsList = new ArrayList<>();
		for(LocalDate date: dates) {
			String val = String.valueOf(date);
			String finalVal = val.substring(0, 4);
			yearsList.add(Integer.parseInt(finalVal));
		}
		
		Set<Integer> yearsSet = new LinkedHashSet<>();
		yearsSet.addAll(yearsList);
		
		yearsList.clear();
		yearsList.addAll(yearsSet);
		return yearsList;
	}

	@Override
	public List<MatchEntity> getMatchesByTeamAndYear(String teamName, Integer year) {
		String strDate1 = year + "-01-01";
		String strDate2 = (year + 1) + "-01-01";
		LocalDate date1 = LocalDate.parse(strDate1);
		LocalDate date2 = LocalDate.parse(strDate2);
		
		List<MatchEntity> matchesList = matchRepository.getMatchesByTeamAndYear(teamName, date1, date2);
		
		return matchesList;
	}

	@Override
	public List<Integer> getYearsByTeam(String teamName) {
		List<LocalDate> yearsList = matchRepository.getMatchesDate(teamName);
		List<Integer> yearsListInt = new ArrayList<>();
		
		for(LocalDate date: yearsList) {
			String val = String.valueOf(date);
			val = val.substring(0, 4);
			yearsListInt.add(Integer.parseInt(val));
		}

		Set<Integer> yearsSetString = new LinkedHashSet<>();
		yearsSetString.addAll(yearsListInt);

		yearsListInt.clear();
		yearsListInt.addAll(yearsSetString);
		
		return yearsListInt;
	}

}