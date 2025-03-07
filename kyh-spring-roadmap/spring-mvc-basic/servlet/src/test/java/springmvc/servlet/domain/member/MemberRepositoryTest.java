package springmvc.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    // 회원 저장소 반환
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 매 번 테스트 후 실행
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    // 회원 저장과 회원 조회
    @Test
    void save() {
        // 회원 생성
        Member member = new Member("hello", 20);
        // 회원 저장
        Member savedMember = memberRepository.save(member);
        // 회원 저장소에서 회원 저장 후 반환된 회원의 ID로 조회한 회원 반환
        Member findMember = memberRepository.findById(savedMember.getId());
        // 저장한 회원과 조회된 회원이 같은 지 검증
        assertThat(findMember).isEqualTo(savedMember);
    }

    // 모든 회원 조회
    @Test
    void findAll() {
        // 회원 생성
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        // 회원 저장
        memberRepository.save(member1);
        memberRepository.save(member2);
        // 회원 저장소에서 모든 회원 반환
        List<Member> result = memberRepository.findAll();
        // 반환한 회원의 수가 2인지 검증
        assertThat(result.size()).isEqualTo(2);
        // 반환한 회원 중 저장한 회원이 포함되어 있는지 검증
        assertThat(result).contains(member1, member2);
    }
}
