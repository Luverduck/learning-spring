package springcore.basic.member;

public class MemberServiceImpl implements MemberService {

    // 회원 데이터 접근 객체
    private final MemberRepository memberRepository;

    // 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
