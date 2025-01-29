package springdb.jdbc.repository;

import org.junit.jupiter.api.Test;
import springdb.jdbc.domain.Member;

import java.sql.SQLException;

class MemberRepositoryV0Test {

    // 회원 저장소 생성
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // 회원 정보 설정
        Member member = new Member("memberV0", 10000);
        // 설정한 회원 정보를 DB에 저장
        repository.save(member);
    }
}