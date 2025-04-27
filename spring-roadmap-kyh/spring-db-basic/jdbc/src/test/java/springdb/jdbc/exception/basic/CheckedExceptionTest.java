package springdb.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedExceptionTest {
    /**
     * 검사 예외 (Checked Exception)
     * Exception을 상속한 예외는 검사 예외가 된다.
     */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    // 서비스
    static class Service {
        // 저장소
        Repository repository = new Repository();
        // 예외를 잡아서 처리하는 메소드
        public void callCatchException() {
            // try 문에서 발생한 예외를 catch 절에서 잡아서 처리한다.
            try {
                repository.call();
            } catch(Exception e) {
                // 예외 처리 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }
        // 예외를 상위 호출자에게 던지는 메소드
        public void callThrowException() throws MyCheckedException {
            // 메소드 실행 중 발생한 예외를 상위 호출자에게 던진다.
            repository.call();
        }
    }

    // 저장소
    static class Repository {
        // 예외를 발생시키는 메소드
        // - 검사 예외는 컴파일러가 검사할 수 있으며 예외 처리가 강제된다.
        public void call() throws MyCheckedException {
            throw new MyCheckedException("검사 예외 발생");
        }
    }

    // 검사 예외 처리
    @DisplayName("검사 예외 처리")
    @Test
    void checkedExceptionCatchTest() {
        // 저장소에서 예외를 처리하므로 서비스에 예외가 전달되지 않는다.
        Service service = new Service();
        service.callCatchException();
    }

    // 검사 예외 던지기
    @DisplayName("검사 예외 던지기")
    @Test
    void checkedExceptionThrowTest() {
        // 저장소에서 예외를 던지므로 서비스에 예외가 전달된다.
        Service service = new Service();
        assertThatThrownBy(() -> service.callThrowException()).isInstanceOf(MyCheckedException.class);
    }
}
