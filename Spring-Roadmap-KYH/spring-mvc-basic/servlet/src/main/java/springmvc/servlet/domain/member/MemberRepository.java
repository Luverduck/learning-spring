package springmvc.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 여기서는 동시성 문제를 고려하지 않음
 * 실무에서는 ConcurrentHashMap이나 AtomicLong 사용을 고려해야 함
 */
public class MemberRepository {

    // 회원 정보 저장소
    private static Map<Long, Member> store = new HashMap<>();
    // 회원 아이디 시퀀스
    private static long sequence = 0L;

    // 생성자 주입 (싱글톤)
    private static final MemberRepository instance = new MemberRepository();
    private MemberRepository() { }
    // 싱글톤 객체 반환
    public static MemberRepository getInstance() {
        return instance;
    }

    // 회원 정보 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    
    // 회원 정보 조회
    public Member findById(Long id) {
        return store.get(id);
    }

    // 모든 회원 정보 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 회원 저장소 비우기
    public void clearStore() {
        store.clear();
    }
}
