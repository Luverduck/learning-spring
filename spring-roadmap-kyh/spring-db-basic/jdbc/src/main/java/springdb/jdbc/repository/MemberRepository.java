package springdb.jdbc.repository;

import springdb.jdbc.domain.Member;

public interface MemberRepository {

    // 회원 정보 저장
    Member save(Member member);

    // 회원 정보 조회
    Member findById(String memberId);

    // 회원 정보 수정
    void update(String memberId, int money);

    // 회원 정보 삭제
    void delete(String memberId);
}
