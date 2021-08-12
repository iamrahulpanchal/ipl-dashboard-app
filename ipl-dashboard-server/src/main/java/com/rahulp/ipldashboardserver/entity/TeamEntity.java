package com.rahulp.ipldashboardserver.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "team")
public class TeamEntity {

    @Override
    public String toString() {
        return "TeamEntity [teamName=" + teamName + ", totalMatches=" + totalMatches + ", wins=" + wins + ", losses=" +
            losses + ", noResult=" + noResult + "]";
    }
    
    @Id
    private String teamName;
    private Long totalMatches;
    private Long wins;
    private Long losses;
    private Long noResult;
    
    @Transient
    private List<MatchEntity> first4Matches;

    public List<MatchEntity> getFirst4Matches() {
		return first4Matches;
	}
	public void setFirst4Matches(List<MatchEntity> first4Matches) {
		this.first4Matches = first4Matches;
	}
	public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public Long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(Long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public Long getWins() {
        return wins;
    }
    public void setWins(Long wins) {
        this.wins = wins;
    }
    public Long getLosses() {
        return losses;
    }
    public void setLosses(Long losses) {
        this.losses = losses;
    }
    public Long getNoResult() {
        return noResult;
    }
    public void setNoResult(Long noResult) {
        this.noResult = noResult;
    }

}