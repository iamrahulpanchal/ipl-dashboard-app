package com.rahulp.ipldashboardserver.batchprocessing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rahulp.ipldashboardserver.entity.TeamEntity;

@Component
@Transactional
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	
	  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	  @PersistenceContext
	  private EntityManager em;
	
	  @Override
	  public void afterJob(JobExecution jobExecution) {
		    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			      log.info("!!! JOB FINISHED! Time to verify the results");

			      Query distinctTeam1Query = em.createQuery("SELECT DISTINCT(m.team1) FROM MatchEntity m");
			      Query distinctTeam2Query = em.createQuery("SELECT DISTINCT(m.team2) FROM MatchEntity m");
			      
			      @SuppressWarnings("unchecked")
			      List<String> team1List = distinctTeam1Query.getResultList();
			      @SuppressWarnings("unchecked")
			      List<String> team2List = distinctTeam2Query.getResultList();
			      
			      Set<String> teamsList = new HashSet<>();
			      teamsList.addAll(team1List);
			      teamsList.addAll(team2List);
			      
			      for(String teamVal: teamsList) {
					  
			    	  Query totalMatchesQuery = em.createQuery("SELECT count(m) FROM MatchEntity m where m.team1 = :team OR m.team2 = :team");
			    	  totalMatchesQuery.setParameter("team", teamVal);
			    	  Long totalMatches = (Long) totalMatchesQuery.getSingleResult();
			    	  
			    	  Query winsQuery = em.createQuery("SELECT count(m) FROM MatchEntity m where m.matchWinner = :team");
			    	  winsQuery.setParameter("team", teamVal);
			    	  Long wins = (Long) winsQuery.getSingleResult();
			    	  
			    	  Query noResultQuery = em.createQuery("SELECT count(m) FROM MatchEntity m where m.matchWinner = 'NA' AND (m.team1 = :team OR m.team2 = :team)");
			    	  noResultQuery.setParameter("team", teamVal);
			    	  Long noResult = (Long) noResultQuery.getSingleResult();
			    	  
			    	  Long losses = totalMatches - wins - noResult;
			    	  
			    	  TeamEntity team = new TeamEntity();
			    	  team.setTeamName(teamVal);
			    	  team.setTotalMatches(totalMatches);
			    	  team.setWins(wins);
			    	  team.setLosses(losses);
			    	  team.setNoResult(noResult);
			    	  
			    	  em.persist(team);
			    	  
			    	  // Overriden toString() of TeamEntity
			    	  log.info(team + "");
			      }
			      
		    }
	  }
}
