package vn.com.micareer.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.SQLException;
import vn.com.micareer.util.JobExpiryScheduler;

@WebListener
public class AppLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[AppLifecycleListener] Đang khởi động miCareer...");
        
        // Gọi thử 1 connection (ping) để ép class DBContext được nạp vào bộ nhớ 
        // Khởi tạo Pool
        try (Connection conn = DBContext.getConnection()) {
            System.out.println("[AppLifecycleListener] Kết nối thành công | HikariCP Connection Pool đã được khởi tạo!");
        } catch (SQLException e) {
            System.err.println("[AppLifecycleListener] LỖI: Không thể kết nối tới CSDL!");
        }
        
        JobExpiryScheduler.start();
        System.out.println("[AppLifecycleListener] JobExpiryScheduler started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[AppLifecycleListener] shutdown miCareer...");
        JobExpiryScheduler.stop();
        System.out.println("[AppLifecycleListener] JobExpiryScheduler stopped");
        DBContext.closePool();
        
        System.out.println("[AppLifecycleListener] Đã đóng HikariCP Connection Pool!");
    }
 
}
