package springdb.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static springdb.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    // DriverManager를 사용하여 DB 커넥션 얻기
    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    // DriverManagerDataSource를 사용하여 DB 커넥션 얻기
    @Test
    void dataSourceDriverManager() throws SQLException {
        // DriverManagerDataSource 설정
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        // DB 커넥션 얻기
        useDataSource(dataSource);
    }

    // HikariCP를 사용하여 DB 커넥션 얻기
    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // HikariDataSource 설정
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10); // 커넥션 풀 사이즈
        dataSource.setPoolName("MyConnectionPool"); // 커넥션 풀 이름
        // DB 커넥션 얻기
        useDataSource(dataSource);
        // 커넥션 풀에서 커넥션이 생성되는 로그를 출력하기 위함
        Thread.sleep(1000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        // 사용
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }
}
