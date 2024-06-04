package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// [03-03] START
public class MemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    // 매번 테스트가 끝날 때마다 실행
    @AfterEach
    public void afterEach() {
        repository.clearStorage();
    }

    // 저장소에 회원 저장 기능 테스트
    @Test
    public void save() {
        // 저장소에 회원 저장
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        // 저장소에서 ID로 회원 조회
        Member result = repository.findById(member.getId()).get();

        // 실행 결과 확인
        //System.out.println("result = " + (result == member));

        // JUnit의 Assertions 클래스를 이용한 테스트 케이스 작성
        // 1. org.junit.jupiter.api.Assertions
        //Assertions.assertEquals(member, result);

        // 2. org.assertj.core.api.Assertions
        assertThat(member).isEqualTo(result);
    }

    // 저장소에서 ID로 회원 조회 기능 테스트
    @Test
    public void findById() {
        // TO DO : 둘 이상의 회원을 저장한 후 특정 ID의 회원을 조회
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findById(member1.getId()).get();
        assertThat(result).isEqualTo(member1);
    }

    // 저장소에서 모든 회원 조회 기능 테스트
    @Test
    public void findAll() {
        // TO DO : 둘 이상의 회원을 저장한 후 저장소에 저장된 모든 회원을 조회
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //assertThat(result.size()).isEqualTo(3);
    }
}
// [03-03] END