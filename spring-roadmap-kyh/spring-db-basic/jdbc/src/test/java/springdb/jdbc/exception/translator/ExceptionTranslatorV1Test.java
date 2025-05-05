package springdb.jdbc.exception.translator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import springdb.jdbc.domain.Member;
import springdb.jdbc.repository.exceptioin.MyDbException;
import springdb.jdbc.repository.exceptioin.MyDuplicateKeyException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static springdb.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ExceptionTranslatorV1Test {

    // 회원 저장소
    Repository repository;
    // 회원 서비스
    Service service;

    // 매 번 테스트 전 실행
    @BeforeEach
    void init() {
        // DataSource 생성
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        // 회원 저장소 생성
        repository = new Repository(dataSource);
        // 회원 서비스 생성
        service = new Service(repository);
    }

    @Test
    @DisplayName("키 중복 오류 처리")
    void duplicateKeySave() {
        // 동일한 키 값으로 데이터 저장
        service.create("myId");
        service.create("myId");
    }

    // 회원 서비스
    @RequiredArgsConstructor
    static class Service {
        // 저장소
        private final Repository repository;
        // 회원 정보 저장
        public void create(String memberId) {
            try {
                repository.save(new Member(memberId, 0));
                log.info("saveId={}", memberId);
            } catch (MyDuplicateKeyException e) {
                // 키 중복 오류가 발생할 경우 회원 ID를 재생성하여 다시 회원 정보 저장 시도
                log.info("키 중복, 복구 시도");
                String retryId = generateNewId(memberId);
                log.info("retryId={}", retryId);
                repository.save(new Member(retryId, 0));
            } catch (MyDbException e) {
                // 그 외 오류가 발생할 경우 예외 던지기
                log.info("데이터 접근 계층 예외", e);
                throw e;
            }
        }
        // 회원 ID 생성
        private String generateNewId(String memberId) {
            return memberId + new Random().nextInt(10000);
        }
    }

    // 회원 저장소
    @RequiredArgsConstructor
    static class Repository {
        // DataSource
        private final DataSource dataSource;
        // 회원 정보 저장
        public Member save(Member member) {
            // 실행할 SQL
            String sql = "INSERT INTO member(member_id, money) values(?, ?)";
            Connection con = null;
            PreparedStatement pstmt = null;
            try {
                // 커넥션 생성
                con = dataSource.getConnection();
                // 실행할 SQL 쿼리 설정
                pstmt = con.prepareStatement(sql);
                // SQL 쿼리 파라미터 바인딩
                pstmt.setString(1, member.getMemberId());
                pstmt.setInt(2, member.getMoney());
                // SQL 쿼리 실행 후 회원 정보 반환
                pstmt.execute();
                return member;
            } catch (SQLException e) {
                // 키 중복 오류가 발생할 경우
                if(e.getErrorCode() == 23505) {
                    // 발생한 예외를 체이닝하여 MyDuplicateKeyException 타입 예외 던지기
                    throw new MyDuplicateKeyException(e);
                }
                // 발생한 예외를 체이닝하여 MyDbException 타입 예외 던지기
                throw new MyDbException(e);
            } finally {
                // Statement의 자원 해제
                JdbcUtils.closeStatement(pstmt);
                // Connection의 자원 해제
                JdbcUtils.closeConnection(con);
            }
        }
    }
}
