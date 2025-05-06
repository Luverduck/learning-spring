package springdb.jdbc.exception.translator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static springdb.jdbc.connection.ConnectionConst.*;

@Slf4j
public class SpringExceptionTranslatorTest {

    // DataSource
    DataSource dataSource;

    // 매 번 테스트 전 실행
    @BeforeEach
    void init() {
        // DataSource 생성
        dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }

    // SQL 문법 오류가 발생할 수 있는 SQL문 실행
    @Test
    void sqlExceptionErrorCode() {
        // SQL 문법 오류가 발생할 수 있는 SQL문
        String sql = "SELECT bad grammar";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            // DB 자원 준비
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            // 쿼리 실행
            pstmt.executeQuery();
        } catch (SQLException e) {
            // 오류 코드 확인
            assertThat(e.getErrorCode()).isEqualTo(42122);
            int errorCode = e.getErrorCode();
            // 오류 코드와 오류 출력
            log.info("errorCode={}", errorCode);
            log.info("error", e);
        } finally {
            // DB 자원 정리
            JdbcUtils.closeStatement(pstmt);
            JdbcUtils.closeConnection(con);
        }
    }

    // 예외 변환기를 사용하는 경우
    @Test
    void exceptionTranslator() {
        // SQL 문법 오류가 발생할 수 있는 SQL문
        String sql = "SELECT bad grammar";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            // DB 자원 준비
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            // 쿼리 실행
            pstmt.executeQuery();
        } catch (SQLException e) {
            // 오류 코드 확인
            assertThat(e.getErrorCode()).isEqualTo(42122);
            // 예외 변환기를 통해 스프링의 예외로 변환
            SQLErrorCodeSQLExceptionTranslator exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
            // - org.springframework.jdbc.BadSqlGrammarException 타입 반환
            DataAccessException resultException = exceptionTranslator.translate("SELECT", sql, e);
            // 변환된 스프링 예외 출력
            log.info("resultException", resultException);
            assertThat(resultException.getClass()).isEqualTo(BadSqlGrammarException.class);
        } finally {
            // DB 자원 정리
            JdbcUtils.closeStatement(pstmt);
            JdbcUtils.closeConnection(con);
        }
    }
}
