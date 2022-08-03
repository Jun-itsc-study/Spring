package quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SendLogCronTrriger {
		private String timer;
		private Class<? extends Job> job;
		Scheduler scheduler;
		
		public SendLogCronTrriger(String timer, Class<? extends Job> job) {
			super();
			this.timer = timer;
			this.job = job;
		}
		
		public void triggerJob() {
			SchedulerFactory factory = new StdSchedulerFactory();
			JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(job.getName(),"group").build();
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("triger","group")
										.withSchedule(CronScheduleBuilder.cronSchedule(timer)).build();
			
			try {
				scheduler = factory.getScheduler();
				scheduler.start();
				scheduler.scheduleJob(jobDetail, cronTrigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
}
