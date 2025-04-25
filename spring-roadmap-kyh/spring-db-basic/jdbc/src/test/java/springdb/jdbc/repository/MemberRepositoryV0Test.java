package springdb.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import springdb.jdbc.domain.Member;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV0Test {

    // 회원 저장소 생성
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // 회원 정보 설정
        Member member = new Member("memberV5", 10000);

        // save() 테스트
        repository.save(member);

        // findById() 테스트
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member equals findMember {}", member.equals(findMember));
        // save()에 사용한 회원 정보와 findById()로 반환환 회원 정보가 같은지 확인
        assertThat(findMember).isEqualTo(member);

        // update() 테스트
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        // update()에 사용한 회원 정보와 findById()로 반환환 회원 정보가 같은지 확인
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // delete() 테스트
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId())).isInstanceOf(NoSuchElementException.class) ;
    }
}