package springcore.basic.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    
    // 회원 정보 저장소
    private static Map<Long, Member> storage = new HashMap<>();

    // 회원 정보 저장 구현
    @Override
    public void save(Member member) {
        storage.put(member.getId(), member);
    }

    // ID로 회원 정보 조회 구현
    @Override
    public Member findByID(Long memberId) {
        return storage.get(memberId);
    }

}
