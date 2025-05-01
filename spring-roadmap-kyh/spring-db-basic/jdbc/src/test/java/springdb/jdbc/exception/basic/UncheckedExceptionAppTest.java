package springdb.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class UncheckedExceptionAppTest {

    // 비 검사 예외 테스트
    @Test
    void checkedExceptionTest() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request()).isInstanceOf(Exception.class);
    }

    // 컨트롤러
    static class Controller {
        Service service = new Service();
        // 컨트롤러의 메소드가 하위 메소드에서 발생할 수 있는 예외 타입에 의존하지 않는다.
        public void request() {
            service.logic();
        }
    }

    // 서비스
    static class Service {
        NetworkClient networkClient = new NetworkClient();
        Repository repository = new Repository();
        // 서비스의 메소드가 하위 메소드에서 발생할 수 있는 예외 타입에 의존하지 않는다.
        public void logic() {
            networkClient.call();
            repository.call();
        }
    }

    // 클라이언트
    static class NetworkClient {
        // 메소드에서 발생할 수 있는 비 검사 예외 생략 가능
        public void call() {
            throw new RuntimeConnectException("서버 연결 에러 발생");
        }
    }

    // 저장소
    static class Repository {
        // 메소드에서 발생할 수 있는 비 검사 예외 생략 가능
        public void call() {
            try {
                // 검사 예외를 발생시키는 메소드 호출
                runSQL();
            } catch(SQLException e) {
                // 검사 예외를 잡아서 비검사 예외를 생성한 후 던지기
                throw new RuntimeSQLException(e);
            }
        }
        // 검사 예외를 발생시키는 메소드
        public void runSQL() throws SQLException {
            throw new SQLException("SQL 에러 발생");
        }
    }

    // 런타임 네트워크 연결 예외
    static class RuntimeConnectException extends RuntimeException {
        // 생성자
        public RuntimeConnectException(String message) {
            super(message);
        }
    }
    
    // 런타임 SQL 예외
    static class RuntimeSQLException extends RuntimeException {
        // 생성자
        public RuntimeSQLException(Throwable cause) {
            // 발생한 예외를 통해 런타임 SQL 예외 생성
            super(cause);
        }
    }
}
