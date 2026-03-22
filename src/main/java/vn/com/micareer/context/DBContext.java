package vn.com.micareer.context;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBContext {

    private static final HikariDataSource dataSource;

    static {
        Properties props = new Properties();
        // Nạp file cấu hình từ Classpath (thư mục resources khi build sẽ nằm ở gốc Classpath)
        try (InputStream input = DBContext.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Lỗi: Không tìm thấy file database.properties trong classpath.");
            }
            props.load(input);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.username"));
            config.setPassword(props.getProperty("db.password"));
            config.setDriverClassName(props.getProperty("db.driver"));

            // Ánh xạ các thông số cấu hình Pool
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("hikari.maximumPoolSize", "10")));
            config.setMinimumIdle(Integer.parseInt(props.getProperty("hikari.minimumIdle", "5")));
            config.setIdleTimeout(Long.parseLong(props.getProperty("hikari.idleTimeout", "30000")));
            config.setConnectionTimeout(Long.parseLong(props.getProperty("hikari.connectionTimeout", "30000")));
            config.setMaxLifetime(Long.parseLong(props.getProperty("hikari.maxLifetime", "1800000")));

            // Cấu hình tối ưu bộ đệm cho MySQL
            config.addDataSourceProperty("cachePrepStmts", props.getProperty("hikari.dataSource.cachePrepStmts", "true"));
            config.addDataSourceProperty("prepStmtCacheSize", props.getProperty("hikari.dataSource.prepStmtCacheSize", "250"));
            config.addDataSourceProperty("prepStmtCacheSqlLimit", props.getProperty("hikari.dataSource.prepStmtCacheSqlLimit", "2048"));
            config.addDataSourceProperty("useServerPrepStmts", props.getProperty("hikari.dataSource.useServerPrepStmts", "true"));

            dataSource = new HikariDataSource(config);

        } catch (IOException e) {
            throw new ExceptionInInitializerError("Không thể khởi tạo cấu hình database: " + e.getMessage());
        }
    }

    private DBContext() {
        throw new UnsupportedOperationException("Không thể khởi tạo thủ công Connection Pool!");
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
