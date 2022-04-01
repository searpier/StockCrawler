package com.antkiller.jobs.tasklet.launcher;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AntApplication {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

		ctx.load("classpath:/resources/com/antkiller/jobs/AntKillerJobs.xml");

		ctx.refresh();

		JobLauncher jobLauncher = ctx.getBean("jobLauncher", JobLauncher.class);

		try {

			Job job1 = ctx.getBean("antJob", Job.class);
			
			JobParameters jobParameters = new JobParametersBuilder()

					.addString("id", UUID.randomUUID().toString())

					.toJobParameters();

			jobLauncher.run(job1, jobParameters);

		} catch (Exception e) {

			e.printStackTrace();

		}

		ctx.close();

	}

}
