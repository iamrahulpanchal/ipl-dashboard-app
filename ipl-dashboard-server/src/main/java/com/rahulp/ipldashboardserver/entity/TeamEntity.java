package com.rahulp.ipldashboardserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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