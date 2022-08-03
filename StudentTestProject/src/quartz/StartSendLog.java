package quartz;


public class StartSendLog{
	private SendLogCronTrriger trigger;

	public StartSendLog() {
		super();
		trigger = new SendLogCronTrriger("0 0 0 1/1 * ? *",SendLogJob.class);
		trigger.triggerJob();
	}

	
}
