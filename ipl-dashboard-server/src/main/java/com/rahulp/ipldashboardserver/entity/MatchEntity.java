package com.rahulp.ipldashboardserver.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MatchEntity {
	
	@Id
	private Long matchId;	
	private String city;	
	private LocalDate date;
	private String playerOfMatch;	
	private String venue;
	private String team1;
	private String team2;
	private String tossWinner;
	private String tossDecision;
	private String matchWinner;	
	private String result;
	private String result_margin;	
	private String method;	
	private String umpire1;	
	private String umpire2;
	
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getPlayerOfMatch() {
		return playerOfMatch;
	}
	public void setPlayerOfMatch(String playerOfMatch) {
		this.playerOfMatch = playerOfMatch;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public String getTossWinner() {
		return tossWinner;
	}
	public void setTossWinner(String tossWinner) {
		this.tossWinner = tossWinner;
	}
	public String getTossDecision() {
		return tossDecision;
	}
	public void setTossDecision(String tossDecision) {
		this.tossDecision = tossDecision;
	}
	public String getMatchWinner() {
		return matchWinner;
	}
	public void setMatchWinner(String matchWinner) {
		this.matchWinner = matchWinner;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResult_margin() {
		return result_margin;
	}
	public void setResult_margin(String result_margin) {
		this.result_margin = result_margin;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUmpire1() {
		return umpire1;
	}
	public void setUmpire1(String umpire1) {
		this.umpire1 = umpire1;
	}
	public String getUmpire2() {
		return umpire2;
	}
	public void setUmpire2(String umpire2) {
		this.umpire2 = umpire2;
	}

}