package springdb.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import springdb.jdbc.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository {

    // DB 커넥션을 관리하는 DataSource
    private final DataSource dataSource;
    // 예외 변환기
    private final SQLExceptionTranslator exceptionTranslator;

    // 생성자 주입
    public MemberRepositoryV4_2(DataSource dataSource) {
        this.dataSource = dataSource;
        // 예외 변환기 빈 주입 (오류 코드를 기반으로 스프링 예외로 변환하는 구현체)
        this.exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    // 회원 정보 저장
    @Override
    public Member save(Member member) {
        // 실행할 SQL 쿼리 준비
        String sql = "insert into member(member_id, money) values(?, ?)";

        // 변수 초기화
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // 커넥션 생성
            con = getConnection();
            // 실행할 SQL 쿼리 설정
            pstmt = con.prepareStatement(sql);
            // SQL 쿼리 파라미터 바인딩
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            // SQL 쿼리 실행
            pstmt.executeUpdate();
            // DB에 등록된 회원 정보 반환
            return member;
        } catch(SQLException e) {
            // 발생한 예외를 스프링 예외로 변환하여 던지기
            throw exceptionTranslator.translate("save", sql, e);
        } finally {
            // DB 자원 해제
            close(con, pstmt, null);
        }
    }

    // 회원 정보 조회
    @Override
    public Member findById(String memberId) {
        // 실행할 SQL 쿼리 준비
        String sql = "select * from member where member_id = ?";

        // 변수 초기화
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 커넥션 생성
            con = getConnection();
            // 실행할 SQL 쿼리 설정
            pstmt = con.prepareStatement(sql);
            // SQL 쿼리 파라미터 바인딩
            pstmt.setString(1, memberId);
            // SQL 쿼리 실행 후 그 결과 저장
            rs = pstmt.executeQuery();
            // SQL 쿼리 실행 결과 반환
            if(rs.next()) { // 최초 호출시 ResultSet의 커서를 0에서 1로 이동 (데이터 존재 유무)
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("Member not found : memberID=" + memberId);
            }
        } catch(SQLException e) {
            // 발생한 예외를 스프링 예외로 변환하여 던지기
            throw exceptionTranslator.translate("findById", sql, e);
        } finally {
            // DB 자원 해제
            close(con, pstmt, rs);
        }
    }

    // 회원 정보 수정
    @Override
    public void update(String memberId, int money) {
        // 실행할 SQL 쿼리 준비
        String sql = "update member set money = ? where member_id = ?";

        // 변수 초기화
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 커넥션 생성
            con = getConnection();
            // 실행할 SQL 쿼리 설정
            pstmt = con.prepareStatement(sql);
            // SQL 쿼리 파라미터 바인딩
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            // SQL 쿼리 실행 후 영향받은 행의 수 반환
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch(SQLException e) {
            // 발생한 예외를 스프링 예외로 변환하여 던지기
            throw exceptionTranslator.translate("update", sql, e);
        } finally {
            // DB 자원 해제
            close(con, pstmt, rs);
        }
    }

    // 회원 정보 삭제
    @Override
    public void delete(String memberId) {
        // 실행할 SQL 쿼리 준비
        String sql = "delete from member where member_id = ?";

        // 변수 초기화
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 커넥션 생성
            con = getConnection();
            // 실행할 SQL 쿼리 설정
            pstmt = con.prepareStatement(sql);
            // SQL 쿼리 파라미터 바인딩
            pstmt.setString(1, memberId);
            // SQL 쿼리 실행 후 영향받은 행의 수 반환
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch(SQLException e) {
            // 발생한 예외를 스프링 예외로 변환하여 던지기
            throw exceptionTranslator.translate("delete", sql, e);
        } finally {
            // DB 자원 해제
            close(con, pstmt, rs);
        }
    }

    // DB 커넥션 생성
    private Connection getConnection() throws SQLException {
        // DataSourceUtils을 통해 커넥션 획득
        // - 트랜잭션과 동기화된 커넥션 반환
        // - 트랜잭션과 동기화된 커넥션이 없을 경우 새로 생성하거나 커넥션 풀에서 반환
        Connection con = DataSourceUtils.getConnection(dataSource);
        log.info("get connection={}, class={}", con, con.getClass());
        return con;
    }

    // DB 자원 해제
    private void close(Connection con, Statement stmt, ResultSet rs) {
        // ResultSet의 자원 해제
        JdbcUtils.closeResultSet(rs);
        // Statement의 자원 해제
        JdbcUtils.closeStatement(stmt);
        // DataSourceUtils를 통해 커넥션 해제
        // - 트랜잭션과 동기화된 커넥션의 동기화 해제
        // - 트랜잭션과 동기화되지 않은 커넥션일 경우 커넥션 닫기
        DataSourceUtils.releaseConnection(con, dataSource);
    }
}
