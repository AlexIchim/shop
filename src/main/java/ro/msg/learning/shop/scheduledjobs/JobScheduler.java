package ro.msg.learning.shop.scheduledjobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class JobScheduler {

	private Runnable revenueJob;

	@Autowired
	public JobScheduler(Runnable revenueJob) {
		this.revenueJob = revenueJob;
	}

	@Scheduled(cron = "0 0 0 * * *", zone = "UTC")
	public void scheduledRevenue() {
		revenueJob.run();
	}
}
