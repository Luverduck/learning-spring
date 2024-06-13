package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// [06-05] START
@Repository
public class JpaMemberRepository implements MemberRepository {

    // 의존성 주입
    // @Entity가 붙은 객체를 관리
    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPA에서는 아래와 같이 JPQL이라는 객체 지향 쿼리 언어를 사용한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                        .setParameter("name", name)
                        .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }

    @Override
    public void clearStorage() {

    }
}
// [06-05] END
