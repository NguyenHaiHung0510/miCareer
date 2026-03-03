/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

// Nghe khi Web bị ngắt và thực hiện thu hồi connection pool an toàn
@WebListener
public class DongConnectionPool implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Web da duoc khoi chay thanh cong! - DongConnectionPool dang nghe");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 2. Thu hồi Connection Pool
        System.out.println("Web da ngung hoat dong, thuc hien thu hoi Connection Pool");
        DBContext.shutdown(); 

    }
}