package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// [03-04] START
@Service
public class MemberService {

    // 회원 레퍼지토리 생성
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // [04-02] START
    // 회원 레퍼지토리의 선언
    private final MemberRepository memberRepository;

    // 회원 서비스의 생성자 정의
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // MemberService와 MemberRepository 사이의 의존관계 생성
    // 1. SpringConfig 클래스에 @Bean 어노테이션이 붙은 생성자를 실행하여 반환하는 객체를 빈으로 등록한다.
    // 2. 스프링 컨테이너에서 전달받은 MemberRepository형 객체를 인수로 생성자를 호출한다.
    // 3. MemberService(this)형 객체의 멤버인 memberRepository에 스프링 컨테이너에서 전달받은 MemberRepository형 객체를 대입한다.
    // ** 참고 ** 스프링 4.3에서부터 생성자가 하나밖에 없는 스프링 빈에 의존관계를 주입할 때 @Autowired 어노테이션을 생략할 수 있다.
    // [04-02] END

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 동일한 이름의 회원이 이미 존재하는지 확인
        validateDuplicateMember(member);
        // 회원 등록
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 이름으로 중복 회원 검사
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        // 이름으로 회원 조회
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // 동일한 이름의 회원이 이미 존재할 경우 예외 발생
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * ID로 회원 조회
     * @param id
     * @return
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
// [03-04] END
