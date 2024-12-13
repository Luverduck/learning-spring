package springcore.basic.member;

public class MemberServiceImpl implements MemberService {

    // 회원 데이터 접근 객체
    private MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입 구현
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원 조회 구현
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findByID(memberId);
    }
    
}
