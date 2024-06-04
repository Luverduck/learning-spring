package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// [03-04] START
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
