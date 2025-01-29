package springdb.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static springdb.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
            // DB 접속 정보를 통해 커넥션 생성 후 반환
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get Connection={}, class={}", connection, connection.getClass());
            return connection;
        } catch(SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
