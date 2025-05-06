package springdb.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springdb.jdbc.domain.Member;

import javax.sql.DataSource;

@Slf4j
public class MemberRepositoryV5 implements MemberRepository {

    // JdbcTemplate
    private final JdbcTemplate template;

    // 생성자 주입
    public MemberRepositoryV5(DataSource dataSource) {
        // JdbcTemplate 빈 주입
        this.template = new JdbcTemplate(dataSource);
    }

    // 회원 정보 저장
    @Override
    public Member save(Member member) {
        // 실행할 SQL 쿼리 준비
        String sql = "insert into member(member_id, money) values(?, ?)";
        // 쿼리 실행
        int result = template.update(sql, member.getMemberId(), member.getMoney());
        // 등록한 회원 정보 반환
        return member;
    }

    // 회원 정보 조회
    @Override
    public Member findById(String memberId) {
        // 실행할 SQL 쿼리 준비
        String sql = "select * from member where member_id = ?";
        // 쿼리 실행 및 결과 바인딩
        // - 콜백 메소드를 호출하여 ResultSet을 Member 객체로 변환하여 반환
        return template.queryForObject(sql, memberRowMapper(), memberId);
    }

    // 콜백 메소드
    private RowMapper<Member> memberRowMapper() {
        // Member 객체를 생성한 후 ResultSet의 값을 Member 객체에 저장하여 반환
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }

    // 회원 정보 수정
    @Override
    public void update(String memberId, int money) {
        // 실행할 SQL 쿼리 준비
        String sql = "update member set money = ? where member_id = ?";
        // 쿼리 실행
        int result = template.update(sql, money, memberId);
    }

    // 회원 정보 삭제
    @Override
    public void delete(String memberId) {
        // 실행할 SQL 쿼리 준비
        String sql = "delete from member where member_id = ?";
        // 쿼리 실행
        int result = template.update(sql, memberId);
    }
}
