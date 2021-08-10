package com.rahulp.ipldashboardserver.batchprocessing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.rahulp.ipldashboardserver.dto.MatchDTO;
import com.rahulp.ipldashboardserver.entity.MatchEntity;

public class MatchDataProcessor implements ItemProcessor<MatchDTO, MatchEntity> {
	
	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	@Override
	public MatchEntity process(final MatchDTO matchDTO) throws Exception {
	
		final MatchEntity matchEntity = new MatchEntity();
		
		matchEntity.setMatchId(Long.parseLong(matchDTO.getId()));
		matchEntity.setCity(matchDTO.getCity());
		
		// Parsing Input Date to LocalDate of Custom Format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dtoDate = LocalDate.parse(matchDTO.getDate(), formatter);
		matchEntity.setDate(dtoDate);
		
		matchEntity.setPlayerOfMatch(matchDTO.getPlayer_of_match());
		matchEntity.setVenue(matchDTO.getVenue());
		
		// Set team1 to FirstInnings Team & team2 to SecondInnings Team
		String tossWinner = matchDTO.getToss_winner();
		String tossDecision = matchDTO.getToss_decision();
		String team1 = matchDTO.getTeam1();
		String team2 = matchDTO.getTeam2();
		
		String firstInningsTeam = "";
		String secondInningsTeam = "";
		
		if(tossDecision.equals("bat")) {
			firstInningsTeam = tossWinner; 
			if(firstInningsTeam.equals(team1)) {
				secondInningsTeam = team2;
			} else {
				secondInningsTeam = team1;
			}
			
		} else {
			if(tossWinner.equals(team1)) {
				firstInningsTeam = team2;
				secondInningsTeam = team1;
			} else {
				firstInningsTeam = team1;
				secondInningsTeam = team2;
			}
		}
		
		matchEntity.setTeam1(firstInningsTeam);
		matchEntity.setTeam2(secondInningsTeam);
		
		matchEntity.setTossWinner(matchDTO.getToss_winner());
		matchEntity.setTossDecision(matchDTO.getToss_decision());
		matchEntity.setMatchWinner(matchDTO.getWinner());
		matchEntity.setResult(matchDTO.getResult());
		matchEntity.setResultMargin(matchDTO.getResult_margin());
		matchEntity.setMethod(matchDTO.getMethod());
		matchEntity.setUmpire1(matchDTO.getUmpire1());
		matchEntity.setUmpire2(matchDTO.getUmpire2());
	
		log.info("Converting MatchDTO to MatchEntity of Match Id : " + matchDTO.getId());
		
		return matchEntity;
	}

}
