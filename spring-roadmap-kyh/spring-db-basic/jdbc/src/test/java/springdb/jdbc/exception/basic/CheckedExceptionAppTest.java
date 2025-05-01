package springdb.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedExceptionAppTest {

    // 검사 예외 테스트
    @Test
    void checkedExceptionTest() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request()).isInstanceOf(Exception.class);
    }

    // 컨트롤러
    static class Controller {
        Service service = new Service();
        // 컨트롤러의 메소드가 하위 계층의 예외 타입에 의존한다.
        public void request() throws ConnectException, SQLException {
            service.logic();
        }
    }

    // 서비스
    static class Service {
        NetworkClient networkClient = new NetworkClient();
        Repository repository = new Repository();
        // 서비스의 메소드가 하위 계층의 예외 타입에 의존한다.
        public void logic() throws ConnectException, SQLException {
            networkClient.call();
            repository.call();
        }
    }

    // 클라이언트
    static class NetworkClient {
        // 메소드에서 발생할 수 있는 검사 예외 명시
        // 검사 예외를 발생시키는 메소드
        public void call() throws ConnectException {
            throw new ConnectException("서버 연결 에러 발생");
        }
    }

    // 저장소
    static class Repository {
        // 메소드에서 발생할 수 있는 검사 예외 명시
        public void call() throws SQLException {
            runSQL();
        }
        // 검사 예외를 발생시키는 메소드
        public void runSQL() throws SQLException {
            throw new SQLException("SQL 에러 발생");
        }
    }
}
