package com.git.integration.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;

import com.git.integration.common.ApplicationBeans;
import com.git.integration.integration.GitCommitIntegration;

public class RefreshJob implements Job{
	
	@Value("${execute.once}")
	private boolean executeOnce;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		if (executeOnce) {
			RefrershJobInitializer initializer = ApplicationBeans.getBean(RefrershJobInitializer.class);
			initializer.stop();
		}
		
		GitCommitIntegration gitCommitIntegration = ApplicationBeans.getBean(GitCommitIntegration.class);
		gitCommitIntegration.run();
		
	}
	
}
