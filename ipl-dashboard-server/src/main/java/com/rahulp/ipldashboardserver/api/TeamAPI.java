package com.rahulp.ipldashboardserver.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rahulp.ipldashboardserver.entity.MatchEntity;
import com.rahulp.ipldashboardserver.entity.TeamEntity;
import com.rahulp.ipldashboardserver.service.TeamService;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class TeamAPI {
	
	@Autowired
	private TeamService teamService;

	@GetMapping
	public ResponseEntity<List<String>> getAllTeams(){
		List<String> teamsList = teamService.getAllTeams();
		return new ResponseEntity<>(teamsList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/teams/{teamName}")
	public ResponseEntity<TeamEntity> getTeamData(@PathVariable String teamName){
		TeamEntity team = teamService.getTeamData(teamName);
		return new ResponseEntity<>(team, HttpStatus.OK);
	}
	
	@GetMapping(value = "/teams/{teamName}/matches")
	public ResponseEntity<List<MatchEntity>> getMatchesByYear(@PathVariable String teamName, @RequestParam Integer year){
		List<MatchEntity> matchEntity = teamService.getMatchesByTeamAndYear(teamName, year);
		return new ResponseEntity<>(matchEntity, HttpStatus.OK);
	}
	
	@GetMapping(value = "/teams/{teamName}/years")
	public ResponseEntity<List<Integer>> getYearsByTeam(@PathVariable String teamName){
		List<Integer> yearsList = teamService.getYearsByTeam(teamName);
		return new ResponseEntity<>(yearsList, HttpStatus.OK);
	}
}
