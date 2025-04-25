package springdb.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import springdb.jdbc.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - DataSource 사용, JdbcUtils 사용
 */
@Slf4j
public class MemberRepositoryV1 {

    // DataSource
    private final DataSource dataSource;

    // 생성자 주입
    public MemberRepositoryV1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    // 회원 정보 조회
    public Member findById(String memberId) throws SQLException {
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
            log.error("DB Error", e);
            throw e;
        } finally {
            // DB 자원 해제
            close(con, pstmt, rs);
        }
    }

    // 회원 정보 수정
    public void update(String memberId, int money) throws SQLException {
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
            log.error("DB Error", e);
            throw e;
        } finally {
            // DB 자원 해제
            close(con, pstmt, rs);
        }
    }

    // 회원 정보 삭제
    public void delete(String memberId) throws SQLException {
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
            log.error("DB Error", e);
            throw e;
        } finally {
            // DB 자원 해제
            close(con, pstmt, rs);
        }
    }

    // DB 커넥션 생성
    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        log.info("get connection={}, class={}", con, con.getClass());
        return con;
    }

    // DB 자원 해제
    private void close(Connection con, Statement stmt, ResultSet rs) {
        // DB 자원 해제는 ResultSet -> Statement -> Connection 순으로 진행한다. (생성의 역순)
        // JdbcUtils 사용
        // ResultSet의 자원 해제
        JdbcUtils.closeResultSet(rs);
        // Statement의 자원 해제
        JdbcUtils.closeStatement(stmt);
        // Connection의 자원 해제
        JdbcUtils.closeConnection(con);
    }
}
