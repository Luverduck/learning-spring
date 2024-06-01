package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 메모리 기반 저장소
    private static Map<Long, Member> storage = new ConcurrentHashMap<>();

    // 회원 ID 설정 시퀀스
    private static long sequence = 0L;

    // 저장소에 회원 저장
    @Override
    public Member save(Member member) {
        // 시퀀스 값 증가후 회원 ID로 설정
        member.setId(++sequence);
        // 저장소에 저장
        storage.put(member.getId(), member);
        // 저장한 회원 객체 반환
        return member;
    }

    // 저장소에서 ID로 회원 조회
    @Override
    public Optional<Member> findById(Long id) {
        // 저장소에서 id에 해당하는 Member를 반환, 없을 경우 null 반환
        return Optional.ofNullable(storage.get(id));
    }

    // 저장소에서 이름으로 회원 조회
    @Override
    public Optional<Member> findByName(String name) {
        return storage.values() // Map을 Collection 형태로 반환
                .stream()       // Collection에서 스트림 생성
                .filter(member->member.getName().equals(name)) // 조건식을 만족하는 요소들로 구성된 스트림 반환
                .findAny(); // 스트림에서 가장 먼저 발견되는 요소 반환
    }

    // 저장소에서 모든 회원 조회
    @Override
    public List<Member> findAll() {
        // Map에 저장된 모든 값을 List 형태로 반환
        return new ArrayList<>(storage.values());
    }

    // 저장소에서 모든 회원 삭제
    @Override
    public void clearStorage() {
        // Map에 저장된 모든 값을 삭제
        storage.clear();
    }
}
