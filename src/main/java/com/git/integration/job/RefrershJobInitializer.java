package com.git.integration.job;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.git.integration.common.DefaultExceptionHandler;

@Component
public class RefrershJobInitializer{

	private static final String TRIGGER = "refreshTrigger";
	private static final String GROUP = "refreshGroup";
	private static final String JOB = "refreshJob";

	private Scheduler scheduler;
	
	@Value("${refresh.expression}")
	private String refreshExpression;
	
	@PostConstruct
	public void start() throws SchedulerException {
		scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		
		scheduleGitIntegration();
	}

	private void scheduleGitIntegration() {
		try {
			CronTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity(TRIGGER, GROUP)
					.withSchedule(CronScheduleBuilder.cronSchedule(refreshExpression))
					.build();
			
			JobDetail jobDetail = JobBuilder.newJob(RefreshJob.class).withIdentity(JOB, GROUP).build();
			
			scheduler.scheduleJob(jobDetail, trigger);
		
		} catch (Exception e) {
			DefaultExceptionHandler.handleException(e);
		}
	}
	
	public void stop() {
		try {
			JobKey jobKey = new JobKey(JOB, GROUP);
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			DefaultExceptionHandler.handleException(e);
		}
	}

}
