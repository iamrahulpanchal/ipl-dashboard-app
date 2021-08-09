package com.rahulp.ipldashboardserver.batchprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.rahulp.ipldashboardserver.dto.MatchDTO;
import com.rahulp.ipldashboardserver.entity.MatchEntity;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	  @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  
	  private final String[] fields = new String[] {
			  "id", "city", "date", "player_of_match", "venue", "team1", "team2",
			  "toss_winner", "toss_decision", "winner", "result", "result_margin",
			  "method", "umpire1", "umpire2"
	  };
	  
	  @Bean
	  public FlatFileItemReader<MatchDTO> reader() {
	    return new FlatFileItemReaderBuilder<MatchDTO>()
	      .name("matchDtoReader")
	      .resource(new ClassPathResource("ipl-dataset.csv"))
	      .delimited()
	      .names(fields)
	      .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchDTO>() {{
	        setTargetType(MatchDTO.class);
	      }})
	      .build();
	  }
	  
	  @Bean
	  public MatchDataProcessor processor() {
	    return new MatchDataProcessor();
	  }
	  
	  @Bean
	  public JdbcBatchItemWriter<MatchEntity> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<MatchEntity>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT INTO match (match_id, city, date, player_of_month, venue, team1, team2, toss_winner, toss_decision, match_winner, result, result_margin, method, umpire1, umpire2) "
	      		+ "VALUES (:matchId, :city, :date, :playerOfMonth, :venue, :team1, :team2, :tossWinner, :tossDecision, :matchWinner, :result, :resultMargin, :method, :umpire1, :umpire2)")
	      .dataSource(dataSource)
	      .build();
	  }
}