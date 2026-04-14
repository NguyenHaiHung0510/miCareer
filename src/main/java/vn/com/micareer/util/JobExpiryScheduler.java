package vn.com.micareer.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import vn.com.micareer.dao.JobPostingDAO;

public class JobExpiryScheduler {

    private static final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public static void start() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                JobPostingDAO jobDAO = new JobPostingDAO();
                int updated = jobDAO.closeExpiredJobs();
                System.out.println("[Scheduler] Closed jobs: " + updated);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.MINUTES);
    }

    public static void stop() {
        scheduler.shutdown();
    }
}