package springcore.basic.member;

public interface MemberRepository {
    
    // 회원 정보 저장
    void save(Member member);

    // ID로 회원 정보 조회
    Member findByID(Long memberId);
    
}
