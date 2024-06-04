package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

// [03-05] START
class MemberServiceTest {

    MemberService memberService = new MemberService();

    // 회원 가입 기능 테스트
    @Test
    void join() {
        // given
        // 회원 생성
        Member member = new Member();
        member.setName("hello");

        // when
        // 회원 가입
        Long saveId = memberService.join(member);

        // then
        // 회원 가입 후 반환한 ID로 회원 조회
        Member findMember = memberService.findOne(saveId).get();
        // 생성한 회원과 가입한 회원의 이름이 같은지 테스트
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 회원 가입 기능 테스트 (이미 존재하는 이름의 회원)
    @Test
    void joinDuplicated() {
        // given
        // 회원 생성
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        // when
        // 첫 번째 회원의 회원 가입 후 두 번째 회원의 회원 가입
        memberService.join(member1);
        // 발생할 예외와 예외가 발생할 메소드 실행 전달
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
// [03-05] END