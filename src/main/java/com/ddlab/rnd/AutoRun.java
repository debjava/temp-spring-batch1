package com.ddlab.rnd;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AutoRun {
	
	@Autowired
	@Qualifier("importJob")
	private Job importBatchJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@EventListener(ApplicationReadyEvent.class)
	public void run() {
		System.out.println("Started running the application ...");
		launchJob();
	}
	
	private JobExecution launchJob() {
		JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
		JobExecution jobExecution = null;
		try {

			jobExecution = jobLauncher.run(importBatchJob, jobParameters);

		} catch (IllegalArgumentException | JobParametersInvalidException | UnexpectedJobExecutionException
				| JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
			System.err.println("Exception in running the batch job execution ..."+e.getMessage());
			e.printStackTrace();
		}
		
		if (jobExecution == null) {
			System.err.println("Failed to execute job");
			return jobExecution;
		}
		return jobExecution;
	}

}
