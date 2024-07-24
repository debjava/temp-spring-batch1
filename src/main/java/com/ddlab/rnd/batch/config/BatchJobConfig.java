package com.ddlab.rnd.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.ddlab.rnd.batch.processor.UpdateItemProcessor;
import com.ddlab.rnd.batch.reader.UpdateItemReader;
import com.ddlab.rnd.batch.reader.UpdateItemReader2;
import com.ddlab.rnd.batch.writer.UpdateItemWriter;
import com.ddlab.rnd.entity.Person;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

	@Value("${batch.chunk.size}")
	private String batchChunkSize;
	
	@Autowired
	@Qualifier("updateItemReader1")
	private UpdateItemReader updateItemReader;
	
	@Autowired
	@Qualifier("updateItemReader2")
	private UpdateItemReader2 updateItemReader2;

	@Bean(name = "importJob")
	public Job sfdcOpportunityImportJob(JobRepository jobRepository, @Qualifier("updateStep1") Step updateStep1) {
		return new JobBuilder("importJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(updateStep1)
//				.next(insertStep2)
//				 .listener(sfdcJobCompletionListener()) // Later
				.build();
	}

	@Bean("updateStep1")
	public Step updateStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("updateStep1", jobRepository)
				.chunk(Integer.parseInt(batchChunkSize), transactionManager).faultTolerant().skip(Exception.class)
				.skipLimit(9999999)
//				.listener(sfdcOpportunitySkipListener)
				.reader(updateItemReader2)
//				.reader(updateItemReader())
				.processor(updateItemProcessor())
				.writer(updateItemWriter())
				.build();
	}

//	@Bean("jobInsertStep")
//	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//		return new StepBuilder("insertStep", jobRepository).chunk(Integer.parseInt(batchChunkSize), transactionManager)
//				.faultTolerant().skip(Exception.class).skipLimit(9999999)
////				.listener(sfdcOpportunitySkipListener)
////				.reader(sfdcOpportunityItemReader)
////				.processor(sfdcOpportunityNewItemProcessor)
////				.writer(sfdcOpportunityNewItemWriter)
//				.build();
//
//	}

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

	@Bean
	public ItemProcessor updateItemProcessor() {
		return new UpdateItemProcessor();
	}
	
	
	public ItemWriter<Person> updateItemWriter() {
		return new UpdateItemWriter();
	}
	
	
}
