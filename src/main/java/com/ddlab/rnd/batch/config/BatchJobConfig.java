package com.ddlab.rnd.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ddlab.rnd.batch.reader.UpdateItemReader;
import com.ddlab.rnd.batch.reader.UpdateItemReader2;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

//	@Value("${batch.chunk.size}")
//	private String batchChunkSize;
//	
//	@Autowired
//	@Qualifier("updateItemReader1")
//	private UpdateItemReader updateItemReader;
//	
//	@Autowired
//	@Qualifier("updateItemReader2")
//	private UpdateItemReader2 updateItemReader2;

	@Bean(name = "importJob")
	public Job sfdcOpportunityImportJob(JobRepository jobRepository,
			@Qualifier("updateStep") Step updateStep1, @Qualifier("insertStep") Step insertStep2 ) {
		return new JobBuilder("importJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(updateStep1)
				.next(insertStep2)
//				 .listener(sfdcJobCompletionListener()) // Later
				.build();
	}



//	@Bean
//    public ItemReader<Integer> itemReader() {
//        return new ListItemReader<>(Arrays.asList(1, 2, 3, 4));
//    }
	
	
	// This can also be implemented
//	@Bean
//	public ItemReader<Person> updateItemReader() {
//		List<Person> personList = new ArrayList<>();
//		for (int i = 0; i < 30; i++) {
//			personList.add(new Person(i, "Name-" + i, "Data-" + i));
//		}
//		return new ListItemReader<Person>(personList);
//	}
	
	
}
