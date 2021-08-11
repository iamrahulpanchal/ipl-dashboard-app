package com.rahulp.ipldashboardserver.batchprocessing;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
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
	  
	  @PersistenceUnit
	  public EntityManagerFactory emf;
	  
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
	  public JpaItemWriter<MatchEntity> writer(){
		  	JpaItemWriter<MatchEntity> writer = new JpaItemWriter<>();
	        writer.setEntityManagerFactory(emf);
	        return writer;
	  }
	  
	  @Bean
	  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1(JpaItemWriter<MatchEntity> writer) {
	    return stepBuilderFactory.get("step1")
	      .<MatchDTO, MatchEntity> chunk(10)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer)
	      .build();
	  }
}