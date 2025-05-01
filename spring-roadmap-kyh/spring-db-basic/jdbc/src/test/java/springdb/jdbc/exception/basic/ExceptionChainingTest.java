package springdb.jdbc.exception.basic;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ExceptionChainingTest {

    // 예외 체이닝 된 메소드 테스트
    @Test
    void chainingExceptionTest() {
        Repository repository = new Repository();
        repository.callChaining();
    }

    // 예외 체이닝 되지 않은 메소드 테스트
    @Test
    void unchainingExceptionTest() {
        Repository repository = new Repository();
        repository.callUnchaining();
    }

    // 저장소
    static class Repository {
        // 예외 체이닝 된 메소드
        public void callChaining() {
            try {
                // 검사 예외를 발생시키는 메소드 호출
                runSQL();
            } catch(SQLException e) {
                // 검사 예외를 잡아서 비검사 예외를 생성한 후 던지기
                throw new RuntimeSQLException(e);
            }
        }
        // 예외 체이닝 되지 않은 메소드
        public void callUnchaining() {
            try {
                // 검사 예외를 발생시키는 메소드 호출
                runSQL();
            } catch(SQLException e) {
                // 비검사 예외를 생성한 후 던지기
                throw new RuntimeSQLException();
            }
        }
        // 검사 예외를 발생시키는 메소드
        public void runSQL() throws SQLException {
            throw new SQLException("SQL 에러 발생");
        }
    }

    // 런타임 SQL 예외
    static class RuntimeSQLException extends RuntimeException {
        // 생성자 (기본 생성자)
        public RuntimeSQLException() {
            super();
        }
        // 생성자 (예외 체이닝 생성자)
        public RuntimeSQLException(Throwable cause) {
            // 발생한 예외를 통해 런타임 SQL 예외 생성
            super(cause);
        }
    }
}
