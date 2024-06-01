package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    /**
     * 저장소에 회원 저장
     * @param member
     * @return
     */
    Member save(Member member);

    /**
     * 저장소에서 ID로 회원 조회
     * @param id
     * @return
     */
    Optional<Member> findById(Long id);

    /**
     * 저장소에서 이름으로 회원 조회
     * @param name
     * @return
     */
    Optional<Member> findByName(String name);

    /**
     * 저장소에서 모든 회원 조회
     * @return
     */
    List<Member> findAll();

    /**
     * 저장소에서 모든 회원 삭제
     */
    void clearStorage();
}