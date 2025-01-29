package springdb.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import springdb.jdbc.domain.Member;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    // 회원 저장소 생성
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // 회원 정보 설정
        Member member = new Member("memberV1", 10000);
        // save() 테스트
        repository.save(member);
        // findById() 테스트
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member equals findMember {}", member.equals(findMember));
        // save()에서 사용했던 회원 정보와 findById()로 반환환 회원 정보가 같다
        assertThat(findMember).isEqualTo(member);
    }
}