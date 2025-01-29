package springdb.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import springdb.jdbc.connection.DBConnectionUtil;
import springdb.jdbc.domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {

    // 회원 정보 저장
    public Member save(Member member) throws SQLException {
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
            log.error("DB Error", e);
            throw e;
        } finally {
            // DB 자원 해제
            close(con, pstmt, null);
        }

    }

    // DB 커넥션 생성
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    // DB 자원 해제
    private void close(Connection con, Statement stmt, ResultSet rs) {
        // DB 자원 해제는 ResultSet -> Statement -> Connection 순으로 진행한다. (생성의 역순)
        // ResultSet의 자원 해제
        if(rs != null) {
            try {
                rs.close();
            } catch(SQLException e) {
                log.info("error", e);
            }
        }
        // Statement의 자원 해제
        if(stmt != null) {
            try {
                stmt.close();
            } catch(SQLException e) {
                log.info("error", e);
            }
        }
        // Connection의 자원 해제
        if(con != null) {
            try {
                con.close();
            } catch(SQLException e) {
                log.info("error", e);
            }
        }
    }
}
