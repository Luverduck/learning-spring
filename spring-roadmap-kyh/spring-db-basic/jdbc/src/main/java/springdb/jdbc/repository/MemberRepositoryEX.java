package springdb.jdbc.repository;

import springdb.jdbc.domain.Member;

import java.sql.SQLException;

public interface MemberRepositoryEX {

    // 회원 정보 저장
    Member save(Member member) throws SQLException;

    // 회원 정보 조회
    Member findById(String memberId) throws SQLException;

    // 회원 정보 수정
    void update(String memberId, int money) throws SQLException;

    // 회원 정보 삭제
    void delete(String memberId) throws SQLException;
}
