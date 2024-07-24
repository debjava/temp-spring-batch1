package com.ddlab.rnd.batch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.ddlab.rnd.batch.processor.InsertItemProcessor;
import com.ddlab.rnd.batch.processor.UpdateItemProcessor;
import com.ddlab.rnd.batch.reader.UpdateItemReader;
import com.ddlab.rnd.batch.reader.UpdateItemReader2;
import com.ddlab.rnd.batch.writer.InsertItemWriter;
import com.ddlab.rnd.batch.writer.UpdateItemWriter;
import com.ddlab.rnd.entity.Person;

@Configuration
public class BatchStepConfig {
	
	@Value("${batch.chunk.size}")
	private String batchChunkSize;
	
	@Autowired
	@Qualifier("updateItemReader1")
	private UpdateItemReader updateItemReader;
	
	@Autowired
	@Qualifier("updateItemReader2")
	private UpdateItemReader2 updateItemReader2;
	
	@Autowired
	@Qualifier("insertItemReader")
	private ItemReader<Person> insertItemReader;
	
	@Autowired
	@Qualifier("insertItemProcessor")
	private InsertItemProcessor insertItemProcessor;
	
	@Autowired
	@Qualifier("insertItemWriter")
	private InsertItemWriter insertItemWriter;
//	private ItemWriter<? super Object> insertItemWriter;
//	private ItemWriter<? super Object> insertItemWriter;
//	private ItemWriter<? super Object> insertItemWriter;
	
	@Bean("updateStep")
	public Step updateStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("updateStep", jobRepository)
				.chunk(Integer.parseInt(batchChunkSize), transactionManager)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(9999999)
//				.listener(sfdcOpportunitySkipListener)
				.reader(updateItemReader2)
//				.reader(updateItemReader())
				.processor(updateItemProcessor())
				.writer(updateItemWriter())
				.build();
	}
	
	@Bean("insertStep")
	public Step insertStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("insertStep", jobRepository)
				.chunk(Integer.parseInt(batchChunkSize), transactionManager)
				.reader(insertItemReader)
//				.processor(insertItemProcessor())
				.processor(insertItemProcessor)
				.writer(insertItemWriter)
//				.writer((ItemWriter<? super Object>) insertItemWriter())
				.build();
	}
	
//	@Bean
//	public ItemProcessor<Object, Object> insertItemProcessor() {
//		return new InsertItemProcessor();
//	}
	
//	public ItemWriter<?> insertItemWriter() {
//		return new InsertItemWriter();
//	}
	
//	@Bean
//	public InsertItemProcessor insertItemProcessor() {
//		return new InsertItemProcessor();
//	}
	
	public ItemProcessor updateItemProcessor() {
		return new UpdateItemProcessor();
	}
	
	@Bean("updateItemWriter")
	public ItemWriter updateItemWriter() {
		return new UpdateItemWriter();
	}
	
//	@Bean
//	public ItemProcessor itemProcessor(){
//		return new ItemProcessor<Person, Person>() {
//
//			@Override
//			public Person process(Person item) throws Exception {
//				System.out.println("------------ Inside Item Processor in Insert Step ----------");
////				List<Person> pList = new ArrayList<>();
//				System.out.println("Processed Item: "+item);
//				System.out.println("After Modification");
//				item.setData("Some Data for processing");
//				System.out.println("------------ Inside Item Processor in Insert Step ----------");
//				return item;
//			}
//		};
//	}

}
